package com.ehub.skill;

import static org.assertj.core.api.Assertions.assertThat;

import com.ehub.TestcontainersConfiguration;
import com.ehub.category.Category;
import com.ehub.category.CategoryRepository;
import com.ehub.category.Subcategory;
import com.ehub.category.SubcategoryRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestcontainersConfiguration.class)
class SkillRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Test
    void findsSkillsBySubcategoryId() {
        Category category = new Category();
        category.setName("Web Development");
        categoryRepository.save(category);

        Subcategory subcategory = new Subcategory();
        subcategory.setName("Frontend");
        subcategory.setCategory(category);
        subcategoryRepository.save(subcategory);

        Skill skill = new Skill();
        skill.setName("React");
        skill.setSubcategory(subcategory);
        skillRepository.save(skill);

        assertThat(skillRepository.findBySubcategoryId(subcategory.getId()))
                .extracting(Skill::getName)
                .containsExactly("React");
    }
}
