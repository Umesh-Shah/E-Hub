package com.ehub.qualification;

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
class QualificationRepositoryTest {

    @Autowired
    private QualificationRepository qualificationRepository;

    @Test
    void savesAndReloadsAQualification() {
        Qualification qualification = new Qualification();
        qualification.setName("Bachelor's Degree");
        qualification.setDescription("Undergraduate degree");

        Qualification saved = qualificationRepository.save(qualification);

        assertThat(qualificationRepository.findById(saved.getId()))
                .isPresent()
                .get()
                .satisfies(found -> assertThat(found.getName()).isEqualTo("Bachelor's Degree"));
    }
}
