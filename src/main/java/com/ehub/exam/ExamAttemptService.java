package com.ehub.exam;

import java.time.LocalDateTime;
import java.util.List;

import com.ehub.user.User;
import com.ehub.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class ExamAttemptService {

    private static final Logger log = LoggerFactory.getLogger(ExamAttemptService.class);

    private final ExamAttemptRepository attemptRepository;
    private final ExamAnswerRepository answerRepository;
    private final ExamResultRepository resultRepository;
    private final QuestionRepository questionRepository;
    private final ExamService examService;
    private final UserService userService;

    public ExamAttemptService(
            ExamAttemptRepository attemptRepository,
            ExamAnswerRepository answerRepository,
            ExamResultRepository resultRepository,
            QuestionRepository questionRepository,
            ExamService examService,
            UserService userService) {
        this.attemptRepository = attemptRepository;
        this.answerRepository = answerRepository;
        this.resultRepository = resultRepository;
        this.questionRepository = questionRepository;
        this.examService = examService;
        this.userService = userService;
    }

    @Transactional
    public AttemptResponse start(Long examId, String userEmail) {
        User user = userService.getByEmail(userEmail);
        Exam exam = examService.getOrThrow(examId);

        ExamAttempt attempt = new ExamAttempt();
        attempt.setUser(user);
        attempt.setExam(exam);
        attempt.setStatus(AttemptStatus.IN_PROGRESS);
        attempt.setStartedAt(LocalDateTime.now());
        attemptRepository.save(attempt);
        log.info("User id={} started attempt id={} for exam id={}", user.getId(), attempt.getId(), examId);

        List<QuestionCandidateResponse> questions = questionRepository.findByExamId(examId).stream()
                .map(QuestionCandidateResponse::from)
                .toList();

        return AttemptResponse.from(attempt, questions);
    }

    @Transactional
    public AnswerResponse submitAnswer(Long attemptId, String userEmail, AnswerRequest request) {
        ExamAttempt attempt = getOwnedInProgressAttempt(attemptId, userEmail);

        Question question = questionRepository.findById(request.questionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found"));
        if (!question.getExam().getId().equals(attempt.getExam().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Question does not belong to this exam");
        }
        if (answerRepository.findByAttemptIdAndQuestionId(attemptId, question.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Question already answered in this attempt");
        }

        boolean correct = request.submittedAnswer().trim().equalsIgnoreCase(question.getCorrectAnswer().trim());

        ExamAnswer answer = new ExamAnswer();
        answer.setAttempt(attempt);
        answer.setQuestion(question);
        answer.setSubmittedAnswer(request.submittedAnswer());
        answer.setCorrect(correct);

        return AnswerResponse.from(answerRepository.save(answer));
    }

    @Transactional
    public ExamResultResponse submit(Long attemptId, String userEmail) {
        ExamAttempt attempt = getOwnedInProgressAttempt(attemptId, userEmail);

        int score = answerRepository.findByAttemptId(attemptId).stream()
                .filter(ExamAnswer::isCorrect)
                .mapToInt(answer -> answer.getQuestion().getMarks())
                .sum();

        LocalDateTime now = LocalDateTime.now();
        attempt.setStatus(AttemptStatus.SUBMITTED);
        attempt.setSubmittedAt(now);
        attempt.setScore(score);

        ExamResult result = new ExamResult();
        result.setUser(attempt.getUser());
        result.setExam(attempt.getExam());
        result.setAttempt(attempt);
        result.setScore(score);
        result.setCompletedAt(now);
        resultRepository.save(result);
        log.info("Attempt id={} submitted by user id={} scored {}", attemptId, attempt.getUser().getId(), score);

        return ExamResultResponse.from(result);
    }

    private ExamAttempt getOwnedInProgressAttempt(Long attemptId, String userEmail) {
        ExamAttempt attempt = attemptRepository.findById(attemptId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attempt not found"));

        if (!attempt.getUser().getEmail().equalsIgnoreCase(userEmail)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This attempt belongs to another user");
        }
        if (attempt.getStatus() != AttemptStatus.IN_PROGRESS) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Attempt is already submitted");
        }
        return attempt;
    }
}
