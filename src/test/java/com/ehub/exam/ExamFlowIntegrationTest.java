package com.ehub.exam;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ehub.TestcontainersConfiguration;
import com.ehub.user.Role;
import com.ehub.user.User;
import com.ehub.user.UserRepository;

import com.jayway.jsonpath.JsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * Covers the Phase 4 checkpoint: admin creates an exam and questions, a user takes the exam
 * end to end (start -> answer -> submit) and sees their own result, replacing the legacy
 * app's HttpSession-based scoring with a real persisted attempt/answer model.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
class ExamFlowIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void adminCreatesExamThenUserTakesItAndSeesTheirOwnResult() throws Exception {
        String adminToken = seedAdminAndLogin();
        String userToken = registerUserAndLogin();

        // Public listing works without auth.
        mockMvc.perform(get("/api/exams")).andExpect(status().isOk());

        // Non-admin cannot create an exam.
        mockMvc.perform(post("/api/exams")
                        .header("Authorization", "Bearer " + userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"Blocked Exam","durationMinutes":10,"totalMarks":10}
                                """))
                .andExpect(status().isForbidden());

        MvcResult examResult = mockMvc.perform(post("/api/exams")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"Java Basics","description":"Core Java","durationMinutes":30,"totalMarks":10}
                                """))
                .andExpect(status().isCreated())
                .andReturn();
        long examId = ((Number) JsonPath.read(examResult.getResponse().getContentAsString(), "$.id")).longValue();

        MvcResult q1Result = mockMvc.perform(post("/api/exams/" + examId + "/questions")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"text":"2 + 2?","option1":"3","option2":"4","option3":"5","option4":"6",
                                 "correctAnswer":"4","marks":5}
                                """))
                .andExpect(status().isCreated())
                .andReturn();
        long q1Id = ((Number) JsonPath.read(q1Result.getResponse().getContentAsString(), "$.id")).longValue();

        MvcResult q2Result = mockMvc.perform(post("/api/exams/" + examId + "/questions")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"text":"Capital of France?","option1":"Berlin","option2":"Madrid",
                                 "option3":"Paris","option4":"Rome","correctAnswer":"Paris","marks":5}
                                """))
                .andExpect(status().isCreated())
                .andReturn();
        long q2Id = ((Number) JsonPath.read(q2Result.getResponse().getContentAsString(), "$.id")).longValue();

        MvcResult startResult = mockMvc.perform(post("/api/exams/" + examId + "/attempts")
                        .header("Authorization", "Bearer " + userToken))
                .andExpect(status().isCreated())
                .andReturn();
        String startBody = startResult.getResponse().getContentAsString();
        long attemptId = ((Number) JsonPath.read(startBody, "$.id")).longValue();
        // Candidates must never see the correct answer.
        assertThat(startBody).doesNotContain("correctAnswer");

        mockMvc.perform(post("/api/attempts/" + attemptId + "/answers")
                        .header("Authorization", "Bearer " + userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"questionId\":" + q1Id + ",\"submittedAnswer\":\"4\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/attempts/" + attemptId + "/answers")
                        .header("Authorization", "Bearer " + userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"questionId\":" + q2Id + ",\"submittedAnswer\":\"Rome\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/attempts/" + attemptId + "/submit")
                        .header("Authorization", "Bearer " + userToken))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers
                        .jsonPath("$.score").value(5));

        mockMvc.perform(get("/api/results/me").header("Authorization", "Bearer " + userToken))
                .andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers
                        .jsonPath("$[0].score").value(5))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers
                        .jsonPath("$[0].examName").value("Java Basics"));
    }

    private String seedAdminAndLogin() throws Exception {
        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("User");
        admin.setEmail("exam-admin@example.com");
        admin.setPasswordHash(passwordEncoder.encode("adminpass123"));
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);

        MvcResult loginResult = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"email":"exam-admin@example.com","password":"adminpass123"}
                                """))
                .andExpect(status().isOk())
                .andReturn();
        return JsonPath.read(loginResult.getResponse().getContentAsString(), "$.token");
    }

    private String registerUserAndLogin() throws Exception {
        MvcResult registerResult = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"firstName":"Grace","lastName":"Hopper","email":"exam-user@example.com",
                                 "password":"password123"}
                                """))
                .andExpect(status().isCreated())
                .andReturn();
        return JsonPath.read(registerResult.getResponse().getContentAsString(), "$.token");
    }
}
