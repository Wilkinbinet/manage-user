package com.manage.user.controller;

import com.manage.user.dto.UserRegistrationRequest;
import com.manage.user.dto.UserResponse;
import com.manage.user.model.User;
import com.manage.user.service.impl.UserServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    /**
     * Constructs an instance of the UserController.
     *
     * @param userServiceImpl the UserServiceImpl instance
     */
    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    /**
     * Registers a new user based on the provided UserDto and returns the created user as a ResponseEntity.
     *
     * @param userDto the UserDto object representing the new user
     * @return the ResponseEntity with the created UserDto object
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRegistrationRequest userDto) {
        User savedUser = userServiceImpl.registerUser(userDto);
        UserResponse responseDto = new UserResponse();
        BeanUtils.copyProperties(savedUser, responseDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
