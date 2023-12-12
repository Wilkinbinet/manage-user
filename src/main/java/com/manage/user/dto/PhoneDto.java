package com.manage.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * DTO for phone details within the user registration request.
 */
@Data
public class PhoneDto {

    @NotBlank(message = "Phone number is mandatory")
    private String number;

    @NotBlank(message = "City code is mandatory")
    @JsonProperty("citycode")
    private String cityCode;

    @NotBlank(message = "Country code is mandatory")
    @JsonProperty("countrycode")
    private String countryCode;

}