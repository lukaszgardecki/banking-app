package com.example.app.transact.payment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "v_payments")
@Getter
@Setter
public class PaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private Long accountId;
    private Long userId;
    private String beneficiary;
    private String beneficiaryAccNo;
    private BigDecimal amount;
    private String reference;
    private String status;
    private String reasonCode;
    private LocalDateTime createdAt;
}
