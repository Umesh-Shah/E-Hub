package com.ehub.exam;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {

    List<ExamResult> findByUserId(Long userId);
}
