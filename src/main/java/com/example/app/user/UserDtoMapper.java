package com.example.app.user;

public class UserDtoMapper {

    public static User map(UserDto dto) {
        User user = new User();
        user.setFirst_name(dto.getFirst_name());
        user.setLast_name(dto.getLast_name());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setToken(dto.getToken());
        user.setCode(dto.getCode());
        return user;
    }
}
