package com.manage.user.service;

import com.manage.user.config.UserRegistrationConfig;
import com.manage.user.dto.PhoneDto;
import com.manage.user.dto.UserRegistrationRequest;
import com.manage.user.model.User;
import com.manage.user.repository.UserRepository;
import com.manage.user.security.jwt.JwtTokenProvider;
import com.manage.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.manage.user.util.ConstantsUtil.EMAIL_EXIST;
import static com.manage.user.util.ConstantsUtil.INVALID_EMAIL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private UserRegistrationConfig config;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUserWhenEmailExistsShouldThrowException() {
        UserRegistrationRequest userDto = new UserRegistrationRequest();
        userDto.setEmail("existing@example.com");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User()));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(userDto);
        });

        assertEquals(EMAIL_EXIST, exception.getMessage());
    }

    @Test
    void registerUserWhenEmailIsInvalidShouldThrowException() {
        UserRegistrationRequest userDto = new UserRegistrationRequest();
        userDto.setEmail("invalidemail");
        userDto.setPassword("ValidPassword123!");

        when(config.getEmailPattern()).thenReturn(".+@.+\\..+");
        when(config.getPasswordPattern()).thenReturn("password regex");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(userDto);
        });

        assertEquals(INVALID_EMAIL, exception.getMessage());
    }

    @Test
    void registerUserWhenValidUserShouldRegisterSuccessfully() {
        UserRegistrationRequest userDto = new UserRegistrationRequest();
        userDto.setEmail("valid@example.com");
        userDto.setPassword("Alex@4023456");
        userDto.setPhones(List.of(new PhoneDto()));

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(config.getEmailPattern()).thenReturn(".+@.+\\..+");
        when(config.getPasswordPattern()).thenReturn("password regex");
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(jwtTokenProvider.createToken(any(User.class))).thenReturn("token");
        when(userRepository.save(any(User.class))).thenReturn(new User());
    }
}
