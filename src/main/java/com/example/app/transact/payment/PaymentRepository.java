package com.example.app.transact.payment;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO payments (account_id, beneficiary, beneficiary_acc_no, amount, reference_no, status, reason_code, created_at) " +
            "VALUES (:accountId, :beneficiary, :beneficiaryAccNo, :amount, :referenceNo, :status, :reasonCode, :createdAt)", nativeQuery = true)
    void makePayment(@Param("accountId") Long accountId,
                     @Param("beneficiary") String beneficiary,
                     @Param("beneficiaryAccNo") String beneficiaryAccNo,
                     @Param("amount") BigDecimal amount,
                     @Param("referenceNo") String referenceNo,
                     @Param("status") String status,
                     @Param("reasonCode") String reasonCode,
                     @Param("createdAt") LocalDateTime createdAt);
}
