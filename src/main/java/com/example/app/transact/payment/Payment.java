package com.example.app.transact.payment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long account_id;
    private String beneficiary;
    private String beneficiary_acc_no;
    private BigDecimal amount;
    private String reference_no;
    private String status;
    private String reason_code;
    private LocalDateTime created_at;
}
