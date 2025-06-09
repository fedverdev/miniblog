package com.github.fedverdev.authservice.controllers.rest.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class RegistrationRequest {
    @NotBlank(message = "Username required")
    @JsonProperty(value = "username")
    private String username;

    @NotBlank(message = "Password required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*]).+$",
            message = "The password must contain at least 1 number and 1 special character."
    )
    @JsonProperty(value = "password")
    private String password;

    @NotBlank
    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;

    @JsonProperty(value = "email")
    private String email;
}
