package com.ehub.location;

import static org.assertj.core.api.Assertions.assertThat;

import com.ehub.TestcontainersConfiguration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestcontainersConfiguration.class)
class CityRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Test
    void findsCitiesByCountryIdAndKeepsPostalCode() {
        Country country = new Country();
        country.setName("Canada");
        countryRepository.save(country);

        City city = new City();
        city.setName("Montreal");
        city.setPostalCode("H2X 1Y6");
        city.setCountry(country);
        cityRepository.save(city);

        assertThat(cityRepository.findByCountryId(country.getId()))
                .extracting(City::getName, City::getPostalCode)
                .containsExactly(org.assertj.core.groups.Tuple.tuple("Montreal", "H2X 1Y6"));
    }
}
