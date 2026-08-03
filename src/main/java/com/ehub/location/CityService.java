package com.ehub.location;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class CityService {

    private final CityRepository cityRepository;
    private final CountryService countryService;

    public CityService(CityRepository cityRepository, CountryService countryService) {
        this.cityRepository = cityRepository;
        this.countryService = countryService;
    }

    public Page<CityResponse> findAll(Long countryId, Pageable pageable) {
        Page<City> cities = countryId == null
                ? cityRepository.findAll(pageable)
                : cityRepository.findByCountryId(countryId, pageable);
        return cities.map(CityResponse::from);
    }

    public CityResponse findById(Long id) {
        return CityResponse.from(getOrThrow(id));
    }

    @Transactional
    public CityResponse create(CityRequest request) {
        City city = new City();
        city.setName(request.name());
        city.setPostalCode(request.postalCode());
        city.setCountry(countryService.getOrThrow(request.countryId()));
        return CityResponse.from(cityRepository.save(city));
    }

    @Transactional
    public CityResponse update(Long id, CityRequest request) {
        City city = getOrThrow(id);
        city.setName(request.name());
        city.setPostalCode(request.postalCode());
        city.setCountry(countryService.getOrThrow(request.countryId()));
        return CityResponse.from(city);
    }

    @Transactional
    public CityResponse updatePostalCode(Long id, PostalCodeRequest request) {
        City city = getOrThrow(id);
        city.setPostalCode(request.postalCode());
        return CityResponse.from(city);
    }

    @Transactional
    public void delete(Long id) {
        if (!cityRepository.existsById(id)) {
            throw notFound(id);
        }
        cityRepository.deleteById(id);
    }

    private City getOrThrow(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> notFound(id));
    }

    private ResponseStatusException notFound(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "City %d not found".formatted(id));
    }
}
