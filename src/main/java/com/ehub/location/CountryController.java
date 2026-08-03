package com.ehub.location;

import jakarta.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public PagedModel<CountryResponse> findAll(Pageable pageable) {
        return new PagedModel<>(countryService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public CountryResponse findById(@PathVariable Long id) {
        return countryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CountryResponse create(@Valid @RequestBody CountryRequest request) {
        return countryService.create(request);
    }

    @PutMapping("/{id}")
    public CountryResponse update(@PathVariable Long id, @Valid @RequestBody CountryRequest request) {
        return countryService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        countryService.delete(id);
    }
}
