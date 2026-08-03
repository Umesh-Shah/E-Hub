package com.ehub.qualification;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/qualifications")
public class QualificationController {

    private final QualificationService qualificationService;

    public QualificationController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    @GetMapping
    public PagedModel<QualificationResponse> findAll(Pageable pageable) {
        return new PagedModel<>(qualificationService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public QualificationResponse findById(@PathVariable Long id) {
        return qualificationService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QualificationResponse create(@Valid @RequestBody QualificationRequest request) {
        return qualificationService.create(request);
    }

    @PutMapping("/{id}")
    public QualificationResponse update(@PathVariable Long id, @Valid @RequestBody QualificationRequest request) {
        return qualificationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        qualificationService.delete(id);
    }
}
