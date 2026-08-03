package com.ehub.exam;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ExamService examService;

    public QuestionService(QuestionRepository questionRepository, ExamService examService) {
        this.questionRepository = questionRepository;
        this.examService = examService;
    }

    public List<QuestionAdminResponse> findByExamId(Long examId) {
        return questionRepository.findByExamId(examId).stream().map(QuestionAdminResponse::from).toList();
    }

    @Transactional
    public QuestionAdminResponse create(Long examId, QuestionRequest request) {
        Question question = new Question();
        question.setExam(examService.getOrThrow(examId));
        applyRequest(question, request);
        return QuestionAdminResponse.from(questionRepository.save(question));
    }

    @Transactional
    public QuestionAdminResponse update(Long id, QuestionRequest request) {
        Question question = getOrThrow(id);
        applyRequest(question, request);
        return QuestionAdminResponse.from(question);
    }

    @Transactional
    public void delete(Long id) {
        if (!questionRepository.existsById(id)) {
            throw notFound(id);
        }
        questionRepository.deleteById(id);
    }

    Question getOrThrow(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> notFound(id));
    }

    private void applyRequest(Question question, QuestionRequest request) {
        Set<String> options = Set.of(request.option1(), request.option2(), request.option3(), request.option4());
        if (!options.contains(request.correctAnswer())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "correctAnswer must match one of the four options");
        }

        question.setText(request.text());
        question.setOption1(request.option1());
        question.setOption2(request.option2());
        question.setOption3(request.option3());
        question.setOption4(request.option4());
        question.setCorrectAnswer(request.correctAnswer());
        question.setMarks(request.marks());
    }

    private ResponseStatusException notFound(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Question %d not found".formatted(id));
    }
}
