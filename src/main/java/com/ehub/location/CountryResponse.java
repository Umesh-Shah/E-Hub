package com.ehub.location;

public record CountryResponse(Long id, String name) {

    static CountryResponse from(Country country) {
        return new CountryResponse(country.getId(), country.getName());
    }
}
