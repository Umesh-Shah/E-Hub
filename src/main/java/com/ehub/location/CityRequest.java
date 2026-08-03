package com.ehub.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CityRequest(

        @NotBlank
        @Size(max = 255)
        String name,

        @Size(max = 20)
        String postalCode,

        @NotNull
        Long countryId
) {
}
