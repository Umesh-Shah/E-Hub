package com.ehub.exam;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ExamRequest(

        @NotBlank
        @Size(max = 255)
        String name,

        @Size(max = 1000)
        String description,

        @NotNull
        @Min(1)
        Integer durationMinutes,

        @NotNull
        @Min(1)
        Integer totalMarks
) {
}
