package com.ehub.skill;

public record SkillResponse(Long id, String name, String description, Long subcategoryId, String subcategoryName) {

    static SkillResponse from(Skill skill) {
        return new SkillResponse(
                skill.getId(),
                skill.getName(),
                skill.getDescription(),
                skill.getSubcategory().getId(),
                skill.getSubcategory().getName());
    }
}
