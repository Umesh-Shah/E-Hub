package com.ehub.location;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByCountryId(Long countryId);

    Page<City> findByCountryId(Long countryId, Pageable pageable);
}
