package com.example.app.user.mappers;

import com.example.app.user.User;
import com.example.app.user.UserRole;
import com.example.app.user.dto.UserCredentialsDto;

import java.util.Set;
import java.util.stream.Collectors;

public class UserCredentialsDtoMapper {

    public static UserCredentialsDto map(User user) {

        UserCredentialsDto dto = new UserCredentialsDto();
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        Set<String> roles = user.getRoles()
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        dto.setRoles(roles);
        return dto;
    }
}
