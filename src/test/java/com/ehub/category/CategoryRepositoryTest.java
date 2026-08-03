package com.ehub.category;

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
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void savesAndReloadsACategory() {
        Category category = new Category();
        category.setName("Web Development");
        category.setDescription("Building websites and web apps");

        Category saved = categoryRepository.save(category);

        assertThat(saved.getId()).isNotNull();
        assertThat(categoryRepository.findById(saved.getId()))
                .isPresent()
                .get()
                .satisfies(found -> assertThat(found.getName()).isEqualTo("Web Development"));
    }

    @Test
    void existsByNameIgnoreCaseIsCaseInsensitive() {
        Category category = new Category();
        category.setName("Design");
        categoryRepository.save(category);

        assertThat(categoryRepository.existsByNameIgnoreCase("design")).isTrue();
        assertThat(categoryRepository.existsByNameIgnoreCase("DESIGN")).isTrue();
        assertThat(categoryRepository.existsByNameIgnoreCase("Marketing")).isFalse();
    }
}
