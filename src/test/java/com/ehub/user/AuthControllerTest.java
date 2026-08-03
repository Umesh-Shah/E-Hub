package com.ehub.user;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ehub.config.JwtService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private AuthenticationManager authenticationManager;

    @MockitoBean
    private JwtService jwtService;

    // SecurityConfig pulls this in transitively; addFilters = false means it's never
    // invoked, but the context still needs a bean to satisfy the wiring.
    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;

    private static final String REGISTER_BODY = """
            {"firstName":"Ada","lastName":"Lovelace","email":"ada@example.com","password":"password123"}
            """;

    @Test
    void registersANewUserAndReturnsAToken() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Ada");
        user.setLastName("Lovelace");
        user.setEmail("ada@example.com");
        user.setRole(Role.USER);

        given(userService.register(any())).willReturn(user);
        given(jwtService.generateToken("ada@example.com")).willReturn("token123");
        given(jwtService.expirationSeconds()).willReturn(3600L);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(REGISTER_BODY))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.token", is("token123")))
                .andExpect(jsonPath("$.user.email", is("ada@example.com")));
    }

    @Test
    void rejectsRegistrationWithADuplicateEmail() throws Exception {
        willThrow(new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered"))
                .given(userService).register(any());

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(REGISTER_BODY))
                .andExpect(status().isConflict());
    }

    @Test
    void rejectsLoginWithBadCredentials() throws Exception {
        given(authenticationManager.authenticate(any())).willThrow(new BadCredentialsException("bad creds"));

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"email":"ada@example.com","password":"wrong"}
                                """))
                .andExpect(status().isUnauthorized());
    }
}
