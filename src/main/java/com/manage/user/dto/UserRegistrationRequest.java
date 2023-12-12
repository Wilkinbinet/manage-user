package com.manage.user.dto;

import com.manage.user.config.UserRegistrationConfig;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.*;
import java.util.List;

/**
 * DTO for user registration request.
 */
@Data
public class UserRegistrationRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotEmpty(message = "Phone list cannot be empty")
    private List<PhoneDto> phones;
}