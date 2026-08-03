package com.ehub.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;
    private final CategoryService categoryService;

    public SubcategoryService(SubcategoryRepository subcategoryRepository, CategoryService categoryService) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryService = categoryService;
    }

    public Page<SubcategoryResponse> findAll(Long categoryId, Pageable pageable) {
        Page<Subcategory> subcategories = categoryId == null
                ? subcategoryRepository.findAll(pageable)
                : subcategoryRepository.findByCategoryId(categoryId, pageable);
        return subcategories.map(SubcategoryResponse::from);
    }

    public SubcategoryResponse findById(Long id) {
        return SubcategoryResponse.from(getOrThrow(id));
    }

    @Transactional
    public SubcategoryResponse create(SubcategoryRequest request) {
        Subcategory subcategory = new Subcategory();
        subcategory.setName(request.name());
        subcategory.setDescription(request.description());
        subcategory.setCategory(categoryService.getOrThrow(request.categoryId()));
        return SubcategoryResponse.from(subcategoryRepository.save(subcategory));
    }

    @Transactional
    public SubcategoryResponse update(Long id, SubcategoryRequest request) {
        Subcategory subcategory = getOrThrow(id);
        subcategory.setName(request.name());
        subcategory.setDescription(request.description());
        subcategory.setCategory(categoryService.getOrThrow(request.categoryId()));
        return SubcategoryResponse.from(subcategory);
    }

    @Transactional
    public void delete(Long id) {
        if (!subcategoryRepository.existsById(id)) {
            throw notFound(id);
        }
        subcategoryRepository.deleteById(id);
    }

    public Subcategory getOrThrow(Long id) {
        return subcategoryRepository.findById(id).orElseThrow(() -> notFound(id));
    }

    private ResponseStatusException notFound(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Subcategory %d not found".formatted(id));
    }
}
