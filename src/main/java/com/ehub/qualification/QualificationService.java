package com.ehub.qualification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class QualificationService {

    private final QualificationRepository qualificationRepository;

    public QualificationService(QualificationRepository qualificationRepository) {
        this.qualificationRepository = qualificationRepository;
    }

    public Page<QualificationResponse> findAll(Pageable pageable) {
        return qualificationRepository.findAll(pageable).map(QualificationResponse::from);
    }

    public QualificationResponse findById(Long id) {
        return QualificationResponse.from(getOrThrow(id));
    }

    @Transactional
    public QualificationResponse create(QualificationRequest request) {
        Qualification qualification = new Qualification();
        qualification.setName(request.name());
        qualification.setDescription(request.description());
        return QualificationResponse.from(qualificationRepository.save(qualification));
    }

    @Transactional
    public QualificationResponse update(Long id, QualificationRequest request) {
        Qualification qualification = getOrThrow(id);
        qualification.setName(request.name());
        qualification.setDescription(request.description());
        return QualificationResponse.from(qualification);
    }

    @Transactional
    public void delete(Long id) {
        if (!qualificationRepository.existsById(id)) {
            throw notFound(id);
        }
        qualificationRepository.deleteById(id);
    }

    private Qualification getOrThrow(Long id) {
        return qualificationRepository.findById(id).orElseThrow(() -> notFound(id));
    }

    private ResponseStatusException notFound(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Qualification %d not found".formatted(id));
    }
}
