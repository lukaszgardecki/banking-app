package com.example.app.account;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query(value = "SELECT * FROM accounts WHERE user_id = :id", nativeQuery = true)
    List<Account> findAccountsByUserId(@Param("id") Long userId);

    @Query(value = "SELECT id FROM accounts ORDER BY id DESC LIMIT 0,1", nativeQuery = true)
    Optional<BigDecimal> getMaxId();

}
