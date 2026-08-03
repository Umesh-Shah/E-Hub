package com.ehub.exam;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record QuestionRequest(

        @NotBlank
        @Size(max = 1000)
        String text,

        @NotBlank
        @Size(max = 255)
        String option1,

        @NotBlank
        @Size(max = 255)
        String option2,

        @NotBlank
        @Size(max = 255)
        String option3,

        @NotBlank
        @Size(max = 255)
        String option4,

        @NotBlank
        @Size(max = 255)
        String correctAnswer,

        @NotNull
        @Min(1)
        Integer marks
) {
}
