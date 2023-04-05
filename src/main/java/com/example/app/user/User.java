package com.example.app.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "The first name field cannot be empty")
    @Size(min = 3, max = 50, message = "The first name must be longer than 3 characters")
    private String first_name;
    @NotEmpty(message = "The last name field cannot be empty")
    @Size(min = 3, max = 50, message = "The last name must be longer than 3 characters")
    private String last_name;
    @Email
    @NotEmpty(message = "The email field cannot be empty")
    @Pattern(regexp = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})", message = "Please enter a valid email")
    @Size(max = 255)
    private String email;
    @NotEmpty(message = "The password field cannot be empty")
    @NotNull
    @Size(max = 255)
    private String password;
    @Size(max = 255)
    private String token;
    private Integer code;
    private Integer verified;
    private LocalDateTime verified_at;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<UserRole> roles = new HashSet<>();
}
