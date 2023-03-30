package com.example.app.user.mappers;

import com.example.app.user.User;
import com.example.app.user.dto.UserVerifyingDto;

public class UserVerifyingDtoMapper {

    public static UserVerifyingDto map(User user) {
        UserVerifyingDto dto = new UserVerifyingDto();
        dto.setEmail(user.getEmail());
        dto.setToken(user.getToken());
        dto.setCode(user.getCode());
        return dto;
    }
}
