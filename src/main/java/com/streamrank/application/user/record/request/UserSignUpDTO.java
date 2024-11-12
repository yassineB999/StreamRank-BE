package com.streamrank.application.user.record.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

public record UserSignUpDTO(
        @NotEmpty(message = "Field must not be empty") @NotBlank
        String firstName,
        @NotEmpty(message = "Field must not be empty") @NotBlank
        String lastName,
        @NotEmpty(message = "Field must not be empty") @Email @NotBlank
        String email,
        @NotEmpty(message = "Field must not be empty") @NotBlank
        String userName,
        @NotEmpty(message = "Field must not be empty") @Length(min = 8, max = 20) @NotBlank
        String password,
        Date dateOfBirth
) {
}
