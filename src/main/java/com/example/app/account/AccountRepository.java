package com.example.app.account;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query(value = "SELECT * FROM accounts WHERE user_id = :id", nativeQuery = true)
    List<Account> findAccountsByUserId(@Param("id") Long userId);

    Optional<Account> findAccountById(Long id);


    @Query(value = "SELECT id FROM accounts ORDER BY id DESC LIMIT 0,1", nativeQuery = true)
    Optional<BigDecimal> getMaxId();

    @Modifying
    @Query(value = "INSERT INTO accounts (user_id, account_number, account_name, account_type, created_at) " +
            "VALUES (:userId, :accountNumber, :accountName, :accountType, :createdAt)", nativeQuery = true)
    void createAccount(@Param("userId") Long userId,
                       @Param("accountNumber") String accountNumber,
                       @Param("accountName") String accountName,
                       @Param("accountType") String accountType,
                       @Param("createdAt")LocalDateTime createAt);

    @Modifying
    @Transactional
    @Query(value = "UPDATE accounts SET balance = :newBalance WHERE id = :accountId", nativeQuery = true)
    void changeAccountBalanceById(@Param("newBalance") BigDecimal newBalance, @Param("accountId") Long accountId);

}
