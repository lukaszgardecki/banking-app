package com.example.app.account;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query(value = "SELECT * FROM accounts WHERE user_id = :id", nativeQuery = true)
    List<Account> findAccountsByUserId(Long id);

    @Query(value = "SELECT balance FROM accounts WHERE user_id = :id", nativeQuery = true)
    BigDecimal getTotalBalance(Long id);

    @Query(value = "SELECT id FROM accounts WHERE id = (SELECT MAX(id) FROM accounts)", nativeQuery = true)
    Optional<BigDecimal> getMaxId();
}
