package com.ehub.location;

public record CityResponse(Long id, String name, String postalCode, Long countryId, String countryName) {

    static CityResponse from(City city) {
        return new CityResponse(
                city.getId(),
                city.getName(),
                city.getPostalCode(),
                city.getCountry().getId(),
                city.getCountry().getName());
    }
}
