package com.example.app.user.mappers;

import com.example.app.user.UserRole;
import com.example.app.user.dto.UserCredentialsDto;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserCredentialsDtoMapperTest {

    @Test
    void shouldMapUserToUserCredentialsDto() {
        TestUser user = new TestUser();
        String userEmail = user.getEmail();
        String userPassword = user.getPassword();
        Set<String> userRoles = user.getRoles().stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());

        UserCredentialsDto mappedUser = UserCredentialsDtoMapper.map(user);
        String mappedUserEmail = mappedUser.getEmail();
        String mappedUserPassword = mappedUser.getPassword();
        Set<String> mappedUserRoles = mappedUser.getRoles();

        assertEquals(userEmail, mappedUserEmail);
        assertEquals(userPassword, mappedUserPassword);
        assertEquals(userRoles, mappedUserRoles);
    }
}