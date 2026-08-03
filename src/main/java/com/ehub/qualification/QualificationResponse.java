package com.ehub.qualification;

public record QualificationResponse(Long id, String name, String description) {

    static QualificationResponse from(Qualification qualification) {
        return new QualificationResponse(qualification.getId(), qualification.getName(), qualification.getDescription());
    }
}
