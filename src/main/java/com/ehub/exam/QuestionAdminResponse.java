package com.ehub.exam;

public record QuestionAdminResponse(
        Long id,
        Long examId,
        String text,
        String option1,
        String option2,
        String option3,
        String option4,
        String correctAnswer,
        Integer marks) {

    static QuestionAdminResponse from(Question question) {
        return new QuestionAdminResponse(
                question.getId(),
                question.getExam().getId(),
                question.getText(),
                question.getOption1(),
                question.getOption2(),
                question.getOption3(),
                question.getOption4(),
                question.getCorrectAnswer(),
                question.getMarks());
    }
}
