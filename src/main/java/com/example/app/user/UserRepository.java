package com.example.app.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByTokenAndCode(String token, Integer code);

    Optional<User> findUserByEmail(String email);

    @Query(value = "SELECT verified FROM users WHERE email = :email", nativeQuery = true)
    Integer isUserVerified(String email);

}
