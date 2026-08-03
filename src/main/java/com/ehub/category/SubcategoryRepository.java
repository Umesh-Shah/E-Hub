package com.ehub.category;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    List<Subcategory> findByCategoryId(Long categoryId);

    Page<Subcategory> findByCategoryId(Long categoryId, Pageable pageable);

    boolean existsByCategoryIdAndNameIgnoreCase(Long categoryId, String name);
}
