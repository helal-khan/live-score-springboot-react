package com.konasl.livescore.repository;

import com.konasl.livescore.entity.User;
import com.konasl.livescore.shared.MockResource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    private final User USER_IBRAHIM = MockResource.getMockUserIbrahim();
    private final User USER_SHAHIN = MockResource.getMockUserShahin();

    @BeforeEach
    void setUp() {
        userRepository.save(USER_IBRAHIM);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void shouldReturnOptionalOfUser_whenUsernameIsGiven() {
        Optional<User> returnedUser = userRepository.findByUsername(USER_IBRAHIM.getUsername());
        assertThat(returnedUser.isPresent()).isTrue();
    }

    @Test
    void shouldNotReturnOptionalOfUser_whenUsernameIsGiven() {
        Optional<User> returnedUser = userRepository.findByUsername(USER_SHAHIN.getUsername());
        assertThat(returnedUser.isEmpty()).isTrue();
    }

    @Test
    void shouldReturnTrue_whenUsernameIsGiven() {
        Boolean isUserExist = userRepository.existsByUsername(USER_IBRAHIM.getUsername());
        assertThat(isUserExist).isTrue();
    }

    @Test
    void shouldReturnFalse_whenUsernameIsGiven() {
        Boolean isUserExist = userRepository.existsByUsername(USER_SHAHIN.getUsername());
        assertThat(isUserExist).isFalse();
    }

    @Test
    void shouldReturnTrue_whenEmailIsGiven() {
        Boolean isUserExist = userRepository.existsByUsername(USER_IBRAHIM.getUsername());
        assertThat(isUserExist).isTrue();
    }

    @Test
    void shouldReturnFalse_whenEmailIsGiven() {
        Boolean isUserExist = userRepository.existsByUsername(USER_SHAHIN.getUsername());
        assertThat(isUserExist).isFalse();
    }
}