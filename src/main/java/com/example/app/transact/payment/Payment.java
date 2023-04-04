package com.example.app.transact.payment;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
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
    @Max(50)
    private String beneficiary;
    @Max(255)
    private String beneficiary_acc_no;
    private BigDecimal amount;
    @Max(255)
    private String reference_no;
    @Max(50)
    private String status;
    @Max(100)
    private String reason_code;
    private LocalDateTime created_at;
}
