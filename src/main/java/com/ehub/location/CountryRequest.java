package com.ehub.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CountryRequest(

        @NotBlank
        @Size(max = 255)
        String name
) {
}
