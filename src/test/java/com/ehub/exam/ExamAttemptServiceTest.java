package com.ehub.exam;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.ehub.user.User;
import com.ehub.user.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class ExamAttemptServiceTest {

    @Mock
    private ExamAttemptRepository attemptRepository;

    @Mock
    private ExamAnswerRepository answerRepository;

    @Mock
    private ExamResultRepository resultRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private ExamService examService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ExamAttemptService examAttemptService;

    private User owner;
    private Exam exam;
    private Question question;
    private ExamAttempt attempt;

    @BeforeEach
    void setUp() {
        owner = new User();
        owner.setId(1L);
        owner.setEmail("owner@example.com");

        exam = new Exam();
        exam.setId(100L);
        exam.setName("Java Basics");

        question = new Question();
        question.setId(10L);
        question.setExam(exam);
        question.setCorrectAnswer("Paris");
        question.setMarks(5);

        attempt = new ExamAttempt();
        attempt.setId(1L);
        attempt.setUser(owner);
        attempt.setExam(exam);
        attempt.setStatus(AttemptStatus.IN_PROGRESS);
        attempt.setStartedAt(LocalDateTime.now());
    }

    @Test
    void marksAMatchingAnswerAsCorrect() {
        when(attemptRepository.findById(1L)).thenReturn(Optional.of(attempt));
        when(questionRepository.findById(10L)).thenReturn(Optional.of(question));
        when(answerRepository.findByAttemptIdAndQuestionId(1L, 10L)).thenReturn(Optional.empty());
        when(answerRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        AnswerResponse response =
                examAttemptService.submitAnswer(1L, "owner@example.com", new AnswerRequest(10L, "Paris"));

        assertThat(response.correct()).isTrue();
    }

    @Test
    void marksANonMatchingAnswerAsIncorrect() {
        when(attemptRepository.findById(1L)).thenReturn(Optional.of(attempt));
        when(questionRepository.findById(10L)).thenReturn(Optional.of(question));
        when(answerRepository.findByAttemptIdAndQuestionId(1L, 10L)).thenReturn(Optional.empty());
        when(answerRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        AnswerResponse response =
                examAttemptService.submitAnswer(1L, "owner@example.com", new AnswerRequest(10L, "London"));

        assertThat(response.correct()).isFalse();
    }

    @Test
    void answerMatchingIsCaseAndWhitespaceInsensitive() {
        when(attemptRepository.findById(1L)).thenReturn(Optional.of(attempt));
        when(questionRepository.findById(10L)).thenReturn(Optional.of(question));
        when(answerRepository.findByAttemptIdAndQuestionId(1L, 10L)).thenReturn(Optional.empty());
        when(answerRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        AnswerResponse response =
                examAttemptService.submitAnswer(1L, "owner@example.com", new AnswerRequest(10L, "  paris  "));

        assertThat(response.correct()).isTrue();
    }

    @Test
    void rejectsAnswersFromSomeoneElsesAttempt() {
        when(attemptRepository.findById(1L)).thenReturn(Optional.of(attempt));

        assertThatThrownBy(() -> examAttemptService.submitAnswer(
                        1L, "someone-else@example.com", new AnswerRequest(10L, "Paris")))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(e -> assertThat(((ResponseStatusException) e).getStatusCode())
                        .isEqualTo(HttpStatus.FORBIDDEN));
    }

    @Test
    void rejectsASecondAnswerToTheSameQuestion() {
        when(attemptRepository.findById(1L)).thenReturn(Optional.of(attempt));
        when(questionRepository.findById(10L)).thenReturn(Optional.of(question));
        when(answerRepository.findByAttemptIdAndQuestionId(1L, 10L))
                .thenReturn(Optional.of(new ExamAnswer()));

        assertThatThrownBy(() -> examAttemptService.submitAnswer(
                        1L, "owner@example.com", new AnswerRequest(10L, "Paris")))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(e -> assertThat(((ResponseStatusException) e).getStatusCode())
                        .isEqualTo(HttpStatus.CONFLICT));
    }

    @Test
    void submitComputesScoreFromCorrectAnswersOnly() {
        Question q2 = new Question();
        q2.setId(11L);
        q2.setExam(exam);
        q2.setMarks(3);

        ExamAnswer correctAnswer = new ExamAnswer();
        correctAnswer.setQuestion(question);
        correctAnswer.setCorrect(true);

        ExamAnswer wrongAnswer = new ExamAnswer();
        wrongAnswer.setQuestion(q2);
        wrongAnswer.setCorrect(false);

        when(attemptRepository.findById(1L)).thenReturn(Optional.of(attempt));
        when(answerRepository.findByAttemptId(1L)).thenReturn(List.of(correctAnswer, wrongAnswer));
        when(resultRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        ExamResultResponse response = examAttemptService.submit(1L, "owner@example.com");

        assertThat(response.score()).isEqualTo(5);
        assertThat(attempt.getScore()).isEqualTo(5);
        assertThat(attempt.getStatus()).isEqualTo(AttemptStatus.SUBMITTED);
    }

    @Test
    void rejectsSubmittingAnAlreadySubmittedAttempt() {
        attempt.setStatus(AttemptStatus.SUBMITTED);
        when(attemptRepository.findById(1L)).thenReturn(Optional.of(attempt));

        assertThatThrownBy(() -> examAttemptService.submit(1L, "owner@example.com"))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(e -> assertThat(((ResponseStatusException) e).getStatusCode())
                        .isEqualTo(HttpStatus.CONFLICT));
    }
}
