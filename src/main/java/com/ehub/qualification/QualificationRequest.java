package com.ehub.qualification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record QualificationRequest(

        @NotBlank
        @Size(max = 255)
        String name,

        @Size(max = 1000)
        String description
) {
}
