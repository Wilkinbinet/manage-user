package com.manage.user.controller;

import com.manage.user.dto.UserRegistrationRequest;
import com.manage.user.dto.UserResponse;
import com.manage.user.model.User;
import com.manage.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the UserController class.
 */
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userServiceImpl;

    private UserRegistrationRequest userDto;
    private User user;

    /**
     * Sets up the test environment before each test case.
     */
    @BeforeEach
    void setUp() {
        userDto = new UserRegistrationRequest();
        userDto.setName("Test");
        userDto.setEmail("test@test.com");
        userDto.setPassword("Alex@40235936");

        user = new User();
        user.setName("Test");
        user.setEmail("test@test.com");
    }

    /**
     * Unit test for the registerUser method.
     */
    @Test
    void registerUser() {
        when(userServiceImpl.registerUser(any(UserRegistrationRequest.class))).thenReturn(user);

        ResponseEntity<UserResponse> responseEntity = userController.registerUser(userDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(user.getCreated(), responseEntity.getBody().getCreated());
        assertEquals(user.getId(), responseEntity.getBody().getId());
    }

    /**
     * Unit test for the registerUser method with missing required fields.
     */
    @Test
    void registerUserMissingRequiredFields() {
        UserRegistrationRequest userDto = new UserRegistrationRequest();
        userDto.setEmail("test@test.com");

        assertThrows(IllegalArgumentException.class, () -> userController.registerUser(userDto));
    }

    /**
     * Unit test for the registerUser method with an invalid email.
     */
    @Test
    void registerUserWithInvalidEmail() {
        UserRegistrationRequest userDto = new UserRegistrationRequest();
        userDto.setName("Test");
        userDto.setPassword("password");
        userDto.setEmail("invalid-email");

        assertThrows(IllegalArgumentException.class, () -> userController.registerUser(userDto));
    }
}
