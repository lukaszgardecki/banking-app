package com.example.app.account;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query(value = "SELECT * FROM accounts WHERE user_id = :id", nativeQuery = true)
    List<Account> findAccountsByUserId(Long id);

    @Query(value = "SELECT balance FROM accounts WHERE user_id = :id", nativeQuery = true)
    BigDecimal getTotalBalance(Long id);
}