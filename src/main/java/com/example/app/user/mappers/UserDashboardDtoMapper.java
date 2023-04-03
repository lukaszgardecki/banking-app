package com.example.app.user.mappers;

import com.example.app.user.User;
import com.example.app.user.dto.UserDashboardDto;

public class UserDashboardDtoMapper {

    public static UserDashboardDto map(User user) {
        UserDashboardDto dto = new UserDashboardDto();
        dto.setId(user.getId());
        dto.setFirst_name(user.getFirst_name());
        dto.setLast_name(user.getLast_name());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
