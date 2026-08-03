package com.ehub.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ehub.TestcontainersConfiguration;

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
 * Covers the Phase 3 checkpoint: register -> login -> call a protected endpoint with the JWT,
 * plus the role-based access rules in SecurityConfig (public reads, admin-only writes).
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
class AuthenticationFlowTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String NEW_CATEGORY_BODY = """
            {"name":"Writing","description":"Content and copywriting"}
            """;

    @Test
    void registerThenLoginThenAccessIsRoleGated() throws Exception {
        String registerBody = """
                {"firstName":"Grace","lastName":"Hopper","email":"grace@example.com","password":"password123"}
                """;

        MvcResult registerResult = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerBody))
                .andExpect(status().isCreated())
                .andReturn();
        String registerToken = JsonPath.read(registerResult.getResponse().getContentAsString(), "$.token");
        assertThat(registerToken).isNotBlank();

        MvcResult loginResult = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"email":"grace@example.com","password":"password123"}
                                """))
                .andExpect(status().isOk())
                .andReturn();
        String userToken = JsonPath.read(loginResult.getResponse().getContentAsString(), "$.token");
        assertThat(userToken).isNotBlank();

        // Lookup data is publicly readable without a token.
        mockMvc.perform(get("/api/categories")).andExpect(status().isOk());

        // Writes require authentication at all.
        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(NEW_CATEGORY_BODY))
                .andExpect(status().isUnauthorized());

        // An authenticated non-admin user is forbidden from writing.
        mockMvc.perform(post("/api/categories")
                        .header("Authorization", "Bearer " + userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(NEW_CATEGORY_BODY))
                .andExpect(status().isForbidden());

        // An admin user can write.
        String adminToken = loginAsSeededAdmin();
        mockMvc.perform(post("/api/categories")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(NEW_CATEGORY_BODY))
                .andExpect(status().isCreated());
    }

    private String loginAsSeededAdmin() throws Exception {
        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("User");
        admin.setEmail("admin@example.com");
        admin.setPasswordHash(passwordEncoder.encode("adminpass123"));
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);

        MvcResult loginResult = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"email":"admin@example.com","password":"adminpass123"}
                                """))
                .andExpect(status().isOk())
                .andReturn();

        return JsonPath.read(loginResult.getResponse().getContentAsString(), "$.token");
    }
}
