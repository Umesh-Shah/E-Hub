package com.ehub.skill;

import com.ehub.category.SubcategoryService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class SkillService {

    private final SkillRepository skillRepository;
    private final SubcategoryService subcategoryService;

    public SkillService(SkillRepository skillRepository, SubcategoryService subcategoryService) {
        this.skillRepository = skillRepository;
        this.subcategoryService = subcategoryService;
    }

    public Page<SkillResponse> findAll(Long subcategoryId, Pageable pageable) {
        Page<Skill> skills = subcategoryId == null
                ? skillRepository.findAll(pageable)
                : skillRepository.findBySubcategoryId(subcategoryId, pageable);
        return skills.map(SkillResponse::from);
    }

    public SkillResponse findById(Long id) {
        return SkillResponse.from(getOrThrow(id));
    }

    @Transactional
    public SkillResponse create(SkillRequest request) {
        Skill skill = new Skill();
        skill.setName(request.name());
        skill.setDescription(request.description());
        skill.setSubcategory(subcategoryService.getOrThrow(request.subcategoryId()));
        return SkillResponse.from(skillRepository.save(skill));
    }

    @Transactional
    public SkillResponse update(Long id, SkillRequest request) {
        Skill skill = getOrThrow(id);
        skill.setName(request.name());
        skill.setDescription(request.description());
        skill.setSubcategory(subcategoryService.getOrThrow(request.subcategoryId()));
        return SkillResponse.from(skill);
    }

    @Transactional
    public void delete(Long id) {
        if (!skillRepository.existsById(id)) {
            throw notFound(id);
        }
        skillRepository.deleteById(id);
    }

    private Skill getOrThrow(Long id) {
        return skillRepository.findById(id).orElseThrow(() -> notFound(id));
    }

    private ResponseStatusException notFound(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill %d not found".formatted(id));
    }
}
