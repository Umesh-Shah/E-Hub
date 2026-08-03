package com.ehub.exam;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/results")
public class ExamResultController {

    private final ExamResultService examResultService;

    public ExamResultController(ExamResultService examResultService) {
        this.examResultService = examResultService;
    }

    @GetMapping("/me")
    public List<ExamResultResponse> findMine(Authentication authentication) {
        return examResultService.findMine(authentication.getName());
    }

    @GetMapping
    public PagedModel<ExamResultResponse> findAll(Pageable pageable) {
        return new PagedModel<>(examResultService.findAll(pageable));
    }
}
