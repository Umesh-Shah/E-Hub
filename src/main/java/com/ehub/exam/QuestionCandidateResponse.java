package com.ehub.exam;

/**
 * What a candidate sees while taking an exam: correctAnswer is deliberately omitted
 * (the legacy app had no such distinction and leaked answers to any caller).
 */
public record QuestionCandidateResponse(
        Long id, String text, String option1, String option2, String option3, String option4, Integer marks) {

    static QuestionCandidateResponse from(Question question) {
        return new QuestionCandidateResponse(
                question.getId(),
                question.getText(),
                question.getOption1(),
                question.getOption2(),
                question.getOption3(),
                question.getOption4(),
                question.getMarks());
    }
}
