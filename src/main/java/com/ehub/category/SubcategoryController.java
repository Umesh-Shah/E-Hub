package com.ehub.category;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subcategories")
public class SubcategoryController {

    private final SubcategoryService subcategoryService;

    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @GetMapping
    public PagedModel<SubcategoryResponse> findAll(
            @RequestParam(required = false) Long categoryId, Pageable pageable) {
        return new PagedModel<>(subcategoryService.findAll(categoryId, pageable));
    }

    @GetMapping("/{id}")
    public SubcategoryResponse findById(@PathVariable Long id) {
        return subcategoryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubcategoryResponse create(@Valid @RequestBody SubcategoryRequest request) {
        return subcategoryService.create(request);
    }

    @PutMapping("/{id}")
    public SubcategoryResponse update(@PathVariable Long id, @Valid @RequestBody SubcategoryRequest request) {
        return subcategoryService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        subcategoryService.delete(id);
    }
}
