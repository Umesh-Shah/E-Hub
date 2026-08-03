package com.ehub.exam;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamAttemptController {

    private final ExamAttemptService examAttemptService;

    public ExamAttemptController(ExamAttemptService examAttemptService) {
        this.examAttemptService = examAttemptService;
    }

    @PostMapping("/api/exams/{examId}/attempts")
    @ResponseStatus(HttpStatus.CREATED)
    public AttemptResponse start(@PathVariable Long examId, Authentication authentication) {
        return examAttemptService.start(examId, authentication.getName());
    }

    @PostMapping("/api/attempts/{id}/answers")
    public AnswerResponse submitAnswer(
            @PathVariable Long id, @Valid @RequestBody AnswerRequest request, Authentication authentication) {
        return examAttemptService.submitAnswer(id, authentication.getName(), request);
    }

    @PostMapping("/api/attempts/{id}/submit")
    public ExamResultResponse submit(@PathVariable Long id, Authentication authentication) {
        return examAttemptService.submit(id, authentication.getName());
    }
}
