package com.example.app.user.mappers;

import com.example.app.user.dto.UserDashboardDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDashboardDtoMapperTest {

    @Test
    void shouldMapUserToUserDashboardDto() {
        TestUser user = new TestUser();
        Long userId = user.getId();
        String userFirstName = user.getFirst_name();
        String userLastName = user.getLast_name();
        String userEmail = user.getEmail();

        UserDashboardDto mappedUser = UserDashboardDtoMapper.map(user);
        Long mapperUserId = mappedUser.getId();
        String mappedUserFirstName = mappedUser.getFirst_name();
        String mappedUserLastName = mappedUser.getLast_name();
        String mappedUserEmail = mappedUser.getEmail();

        assertEquals(userId, mapperUserId);
        assertEquals(userFirstName, mappedUserFirstName);
        assertEquals(userLastName, mappedUserLastName);
        assertEquals(userEmail, mappedUserEmail);
    }
}