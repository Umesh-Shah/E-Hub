package com.ehub.exam;

public record ExamResponse(Long id, String name, String description, Integer durationMinutes, Integer totalMarks) {

    static ExamResponse from(Exam exam) {
        return new ExamResponse(
                exam.getId(), exam.getName(), exam.getDescription(), exam.getDurationMinutes(), exam.getTotalMarks());
    }
}
