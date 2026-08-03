package com.ehub.category;

public record CategoryResponse(Long id, String name, String description) {

    static CategoryResponse from(Category category) {
        return new CategoryResponse(category.getId(), category.getName(), category.getDescription());
    }
}
