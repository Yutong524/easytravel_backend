package com.easytravel.easytravel.mdbspringboot.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public record RegisterBody(
        @NotBlank(message = "Username is mandatory")
        @Size(min = 4, message = "Username must be at least four characters long")
        String username,


        @NotBlank(message = "Password is mandatory")
        @Size(min = 8, message = "Password must be at least eight characters long")
        @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one capital letter")
        @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one number")
        String password
) {
}
