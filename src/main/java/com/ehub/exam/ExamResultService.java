package com.ehub.exam;

import java.util.List;

import com.ehub.user.User;
import com.ehub.user.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ExamResultService {

    private final ExamResultRepository examResultRepository;
    private final UserService userService;

    public ExamResultService(ExamResultRepository examResultRepository, UserService userService) {
        this.examResultRepository = examResultRepository;
        this.userService = userService;
    }

    public List<ExamResultResponse> findMine(String userEmail) {
        User user = userService.getByEmail(userEmail);
        return examResultRepository.findByUserId(user.getId()).stream().map(ExamResultResponse::from).toList();
    }

    public Page<ExamResultResponse> findAll(Pageable pageable) {
        return examResultRepository.findAll(pageable).map(ExamResultResponse::from);
    }
}
