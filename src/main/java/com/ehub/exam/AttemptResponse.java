package com.ehub.exam;

import java.time.LocalDateTime;
import java.util.List;

public record AttemptResponse(
        Long id,
        Long examId,
        String examName,
        AttemptStatus status,
        LocalDateTime startedAt,
        LocalDateTime submittedAt,
        Integer score,
        List<QuestionCandidateResponse> questions) {

    static AttemptResponse from(ExamAttempt attempt, List<QuestionCandidateResponse> questions) {
        return new AttemptResponse(
                attempt.getId(),
                attempt.getExam().getId(),
                attempt.getExam().getName(),
                attempt.getStatus(),
                attempt.getStartedAt(),
                attempt.getSubmittedAt(),
                attempt.getScore(),
                questions);
    }
}
