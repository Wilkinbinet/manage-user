package com.manage.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Class representing the API response for user creation.
 */
@Data
public class UserResponse {

    /**
     * Unique identifier for the user.
     */
    private UUID id;

    /**
     * Date and time when the user was created.
     */
    private LocalDateTime created;

    /**
     * Date and time when the user was last updated.
     */
    private LocalDateTime modified;

    /**
     * Date and time of the user's last login.
     */
    @JsonProperty("lastlogin")
    private LocalDateTime lastLogin;

    /**
     * Access token for the API.
     */
    private String token;

    /**
     * Indicates whether the user is active in the system.
     */
    @JsonProperty("isactive")
    private boolean isActive;
}