package com.ehub.exam;

public record AnswerResponse(Long questionId, boolean correct) {

    static AnswerResponse from(ExamAnswer answer) {
        return new AnswerResponse(answer.getQuestion().getId(), answer.isCorrect());
    }
}
