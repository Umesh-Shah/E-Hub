package com.ehub.exam;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/api/exams/{examId}/questions")
    public List<QuestionAdminResponse> findByExam(@PathVariable Long examId) {
        return questionService.findByExamId(examId);
    }

    @PostMapping("/api/exams/{examId}/questions")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionAdminResponse create(@PathVariable Long examId, @Valid @RequestBody QuestionRequest request) {
        return questionService.create(examId, request);
    }

    @PutMapping("/api/questions/{id}")
    public QuestionAdminResponse update(@PathVariable Long id, @Valid @RequestBody QuestionRequest request) {
        return questionService.update(id, request);
    }

    @DeleteMapping("/api/questions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        questionService.delete(id);
    }
}
