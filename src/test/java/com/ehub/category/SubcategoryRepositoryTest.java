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
class SubcategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Test
    void findsSubcategoriesByCategoryId() {
        Category category = new Category();
        category.setName("Web Development");
        categoryRepository.save(category);

        Subcategory frontend = new Subcategory();
        frontend.setName("Frontend");
        frontend.setCategory(category);
        subcategoryRepository.save(frontend);

        Subcategory backend = new Subcategory();
        backend.setName("Backend");
        backend.setCategory(category);
        subcategoryRepository.save(backend);

        assertThat(subcategoryRepository.findByCategoryId(category.getId()))
                .extracting(Subcategory::getName)
                .containsExactlyInAnyOrder("Frontend", "Backend");
    }
}
