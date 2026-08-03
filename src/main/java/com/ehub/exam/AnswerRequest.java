package com.ehub.exam;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerRequest(

        @NotNull
        Long questionId,

        @NotBlank
        String submittedAnswer
) {
}
