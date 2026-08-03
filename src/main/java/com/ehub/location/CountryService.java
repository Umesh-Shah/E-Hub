package com.ehub.location;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Page<CountryResponse> findAll(Pageable pageable) {
        return countryRepository.findAll(pageable).map(CountryResponse::from);
    }

    public CountryResponse findById(Long id) {
        return CountryResponse.from(getOrThrow(id));
    }

    @Transactional
    public CountryResponse create(CountryRequest request) {
        Country country = new Country();
        country.setName(request.name());
        return CountryResponse.from(countryRepository.save(country));
    }

    @Transactional
    public CountryResponse update(Long id, CountryRequest request) {
        Country country = getOrThrow(id);
        country.setName(request.name());
        return CountryResponse.from(country);
    }

    @Transactional
    public void delete(Long id) {
        if (!countryRepository.existsById(id)) {
            throw notFound(id);
        }
        countryRepository.deleteById(id);
    }

    public Country getOrThrow(Long id) {
        return countryRepository.findById(id).orElseThrow(() -> notFound(id));
    }

    private ResponseStatusException notFound(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Country %d not found".formatted(id));
    }
}
