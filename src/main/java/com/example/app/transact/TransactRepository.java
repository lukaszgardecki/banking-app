package com.example.app.transact;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TransactRepository extends CrudRepository<Transact, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO transaction_history (account_id, transaction_type, amount, source, status, reason_code, created_at) " +
            "VALUES (:accountId, :transactType, :amount, :source, :status, :reasonCode, :createdAt)", nativeQuery = true)
    void logTransaction(@Param("accountId") Long accountId,
                        @Param("transactType") String transactType,
                        @Param("amount") BigDecimal amount,
                        @Param("source") String source,
                        @Param("status") String status,
                        @Param("reasonCode") String reasonCode,
                        @Param("createdAt")LocalDateTime createdAt);
}
