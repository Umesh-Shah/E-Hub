package com.ehub.exam;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamAnswerRepository extends JpaRepository<ExamAnswer, Long> {

    List<ExamAnswer> findByAttemptId(Long attemptId);

    Optional<ExamAnswer> findByAttemptIdAndQuestionId(Long attemptId, Long questionId);
}
