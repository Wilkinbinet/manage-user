package com.manage.user.service;

import com.manage.user.dto.AuthenticationDto;

public interface AuthenticationService {
    String login(AuthenticationDto request);
}