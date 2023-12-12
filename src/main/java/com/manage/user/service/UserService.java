package com.manage.user.service;

import com.manage.user.dto.UserRegistrationRequest;
import com.manage.user.model.User;

public interface UserService {
    User registerUser(UserRegistrationRequest user);
}