package com.ehub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_READ_PATTERNS = {
            "/api/categories/**",
            "/api/subcategories/**",
            "/api/skills/**",
            "/api/qualifications/**",
            "/api/countries/**",
            "/api/cities/**"
    };

    private static final String[] ADMIN_ONLY_PATTERNS = {
            "/api/exams/*/questions/**",
            "/api/questions/**"
    };

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Without httpBasic()/formLogin(), Spring Security has no AuthenticationEntryPoint
                // registered and falls back to Http403ForbiddenEntryPoint, which turns every
                // "not authenticated" case into a 403 instead of a 401. Register one explicitly.
                .exceptionHandling(exceptions -> exceptions.authenticationEntryPoint(
                        (request, response, authException) -> response.sendError(HttpStatus.UNAUTHORIZED.value())))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/actuator/health", "/actuator/info").permitAll()
                        .requestMatchers(HttpMethod.GET, PUBLIC_READ_PATTERNS).permitAll()
                        .requestMatchers(PUBLIC_READ_PATTERNS).hasRole("ADMIN")
                        .requestMatchers(ADMIN_ONLY_PATTERNS).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/exams", "/api/exams/*").permitAll()
                        .requestMatchers("/api/exams/*/attempts").authenticated()
                        .requestMatchers("/api/exams/**").hasRole("ADMIN")
                        .requestMatchers("/api/attempts/**").authenticated()
                        .requestMatchers("/api/results/me").authenticated()
                        .requestMatchers("/api/results").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(
            com.ehub.user.CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
