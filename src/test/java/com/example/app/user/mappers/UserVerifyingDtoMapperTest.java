package com.example.app.user.mappers;

import com.example.app.user.dto.UserVerifyingDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserVerifyingDtoMapperTest {

    @Test
    void shouldMapUserToUserDashboardDto() {
        TestUser user = new TestUser();
        String userEmail = user.getEmail();
        String userToken = user.getToken();
        Integer userCode = user.getCode();

        UserVerifyingDto mappedUser = UserVerifyingDtoMapper.map(user);
        String mappedUserEmail = mappedUser.getEmail();
        String mappedUserToken = mappedUser.getToken();
        Integer mappedUserCode = mappedUser.getCode();

        assertEquals(userEmail, mappedUserEmail);
        assertEquals(userToken, mappedUserToken);
        assertEquals(userCode, mappedUserCode);
    }
}