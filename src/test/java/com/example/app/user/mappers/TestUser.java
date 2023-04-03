package com.example.app.user.mappers;

import com.example.app.user.User;
import com.example.app.user.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;

@Getter
@Setter
class TestUser extends User{

    TestUser () {
        UserRole userRole = new UserRole();
        userRole.setId(1L);
        userRole.setName("USER");
        userRole.setDescription("Basic user role");

        UserRole adminRole = new UserRole();
        adminRole.setId(2L);
        adminRole.setName("ADMIN");
        adminRole.setDescription("Admin role");

        HashSet<UserRole> roles = new HashSet<>();
        roles.add(userRole);
        roles.add(adminRole);

        User user = new User();
        user.setId(3L);
        user.setFirst_name("Zbigniew");
        user.setLast_name("Kamiński");
        user.setEmail("z.kaminiski@gmail.com");
        user.setPassword("{bcrypt}$2a$10$VTwtrRs4pMy0Fo/UWQor1u/MrX/Wg/2tO7Pjjbr25ZIrmQys6IpPm");
        user.setToken("piaergtvipg3p4tgvipg6p36gipagv");
        user.setCode(123);
        user.setVerified(1);
        user.setVerified_at(LocalDateTime.now());
        user.setCreated_at(LocalDateTime.now());
        user.setUpdated_at(LocalDateTime.now());
    }
}
