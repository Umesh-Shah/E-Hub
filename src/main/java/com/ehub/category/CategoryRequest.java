package com.ehub.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(

        @NotBlank
        @Size(max = 255)
        String name,

        @Size(max = 1000)
        String description
) {
}
