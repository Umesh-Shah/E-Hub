package com.ehub.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.ehub.TestcontainersConfiguration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestcontainersConfiguration.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findsByEmailCaseInsensitively() {
        User user = new User();
        user.setFirstName("Ada");
        user.setLastName("Lovelace");
        user.setEmail("ada@example.com");
        user.setPasswordHash("hashed");
        user.setRole(Role.USER);
        userRepository.save(user);

        assertThat(userRepository.findByEmailIgnoreCase("ADA@EXAMPLE.COM")).isPresent();
        assertThat(userRepository.existsByEmailIgnoreCase("ada@example.com")).isTrue();
        assertThat(userRepository.existsByEmailIgnoreCase("nobody@example.com")).isFalse();
    }
}
