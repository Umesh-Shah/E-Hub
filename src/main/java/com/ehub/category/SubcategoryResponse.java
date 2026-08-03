package com.ehub.category;

public record SubcategoryResponse(Long id, String name, String description, Long categoryId, String categoryName) {

    static SubcategoryResponse from(Subcategory subcategory) {
        return new SubcategoryResponse(
                subcategory.getId(),
                subcategory.getName(),
                subcategory.getDescription(),
                subcategory.getCategory().getId(),
                subcategory.getCategory().getName());
    }
}
