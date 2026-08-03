package com.ehub.location;

import jakarta.validation.constraints.Size;

public record PostalCodeRequest(

        @Size(max = 20)
        String postalCode
) {
}
