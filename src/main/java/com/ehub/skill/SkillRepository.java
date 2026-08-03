package com.ehub.skill;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findBySubcategoryId(Long subcategoryId);

    Page<Skill> findBySubcategoryId(Long subcategoryId, Pageable pageable);
}
