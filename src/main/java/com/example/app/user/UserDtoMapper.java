package com.example.app.user;

public class UserDtoMapper {

    public static User map(UserDto dto) {
        User user = new User();
        user.setFirst_name(dto.getFirst_name());
        user.setLast_name(dto.getLast_name());
        user.setEmail(dto.getEmail());
        return user;
    }

    public static UserDto map(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirst_name(user.getFirst_name());
        dto.setLast_name(user.getLast_name());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setToken(user.getToken());
        dto.setCode(user.getCode());
        dto.setVerified(user.getVerified());
        dto.setVerified_at(user.getVerified_at());
        dto.setCreated_at(user.getCreated_at());
        dto.setUpdated_at(user.getUpdated_at());
        return dto;
    }
}
