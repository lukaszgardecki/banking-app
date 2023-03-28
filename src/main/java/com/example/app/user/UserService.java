package com.example.app.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void register(UserDto user) {
        User userToSave = UserDtoMapper.map(user);
        userRepository.save(userToSave);
    }
}
