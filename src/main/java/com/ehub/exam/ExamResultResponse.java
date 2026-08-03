package com.ehub.exam;

import java.time.LocalDateTime;

public record ExamResultResponse(
        Long id, Long examId, String examName, Integer score, Integer totalMarks, LocalDateTime completedAt) {

    static ExamResultResponse from(ExamResult result) {
        return new ExamResultResponse(
                result.getId(),
                result.getExam().getId(),
                result.getExam().getName(),
                result.getScore(),
                result.getExam().getTotalMarks(),
                result.getCompletedAt());
    }
}
