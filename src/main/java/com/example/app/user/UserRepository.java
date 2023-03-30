package com.example.app.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByTokenAndCode(String token, Integer code);

    Optional<User> findUserByEmail(String email);

}
