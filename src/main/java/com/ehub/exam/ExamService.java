package com.ehub.exam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public Page<ExamResponse> findAll(Pageable pageable) {
        return examRepository.findAll(pageable).map(ExamResponse::from);
    }

    public ExamResponse findById(Long id) {
        return ExamResponse.from(getOrThrow(id));
    }

    @Transactional
    public ExamResponse create(ExamRequest request) {
        Exam exam = new Exam();
        applyRequest(exam, request);
        return ExamResponse.from(examRepository.save(exam));
    }

    @Transactional
    public ExamResponse update(Long id, ExamRequest request) {
        Exam exam = getOrThrow(id);
        applyRequest(exam, request);
        return ExamResponse.from(exam);
    }

    @Transactional
    public void delete(Long id) {
        if (!examRepository.existsById(id)) {
            throw notFound(id);
        }
        examRepository.deleteById(id);
    }

    public Exam getOrThrow(Long id) {
        return examRepository.findById(id).orElseThrow(() -> notFound(id));
    }

    private void applyRequest(Exam exam, ExamRequest request) {
        exam.setName(request.name());
        exam.setDescription(request.description());
        exam.setDurationMinutes(request.durationMinutes());
        exam.setTotalMarks(request.totalMarks());
    }

    private ResponseStatusException notFound(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Exam %d not found".formatted(id));
    }
}
