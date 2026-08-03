package com.ehub.exam;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import com.ehub.config.JwtService;
import com.ehub.config.SecurityConfig;
import com.ehub.user.CustomUserDetailsService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

// Filters stay enabled (unlike the other slice tests): @WithMockUser relies on the real
// security filter chain to populate the request's Authentication, which this controller reads.
// SecurityConfig is imported explicitly because @WebMvcTest does not auto-detect plain
// @Configuration classes, only Filter/Controller/etc. beans.
@WebMvcTest(ExamAttemptController.class)
@Import(SecurityConfig.class)
class ExamAttemptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ExamAttemptService examAttemptService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    @WithMockUser(username = "candidate@example.com")
    void startsAnAttemptForTheAuthenticatedUser() throws Exception {
        AttemptResponse response = new AttemptResponse(
                1L, 100L, "Java Basics", AttemptStatus.IN_PROGRESS, LocalDateTime.now(), null, null, List.of());
        given(examAttemptService.start(eq(100L), eq("candidate@example.com"))).willReturn(response);

        mockMvc.perform(post("/api/exams/100/attempts"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.status", is("IN_PROGRESS")));
    }

    @Test
    @WithMockUser(username = "candidate@example.com")
    void submitsAnAnswerForTheAuthenticatedUser() throws Exception {
        given(examAttemptService.submitAnswer(eq(1L), eq("candidate@example.com"), any()))
                .willReturn(new AnswerResponse(10L, true));

        mockMvc.perform(post("/api/attempts/1/answers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"questionId":10,"submittedAnswer":"Paris"}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correct", is(true)));
    }

    @Test
    @WithMockUser(username = "candidate@example.com")
    void finalizesAnAttemptForTheAuthenticatedUser() throws Exception {
        given(examAttemptService.submit(eq(1L), eq("candidate@example.com")))
                .willReturn(new ExamResultResponse(1L, 100L, "Java Basics", 10, 10, LocalDateTime.now()));

        mockMvc.perform(post("/api/attempts/1/submit"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score", is(10)));
    }
}
