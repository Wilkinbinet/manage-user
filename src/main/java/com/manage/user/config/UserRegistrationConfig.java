package com.manage.user.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class UserRegistrationConfig {

    @Value("${user.registration.email-pattern}")
    private String emailPattern;

    @Value("${user.registration.password-pattern}")
    private String passwordPattern;

}