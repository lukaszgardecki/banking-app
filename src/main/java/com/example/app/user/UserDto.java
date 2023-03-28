package com.example.app.user;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {
    private Integer id;
    @NotEmpty(message = "The first name field cannot be empty")
    @Size(min = 3, message = "The first name must be longer than 3 characters")
    private String first_name;
    @NotEmpty(message = "The last name field cannot be empty")
    @Size(min = 3, message = "The last name must be longer than 3 characters")
    private String last_name;
    @Email
    @NotEmpty(message = "The email field cannot be empty")
    @Pattern(regexp = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})", message = "Please enter a valid email")
    private String email;
    @NotEmpty(message = "The password field cannot be empty")
    @NotNull
    private String password;
    private String token;
    private Integer code;
    private Integer verified;
    private LocalDateTime verified_at;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
