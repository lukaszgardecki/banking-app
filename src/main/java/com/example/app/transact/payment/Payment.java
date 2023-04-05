package com.example.app.transact.payment;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @Size(max = 50)
    private String beneficiary;
    @Size(max = 255)
    private String beneficiary_acc_no;
    private BigDecimal amount;
    @Size(max = 255)
    private String reference_no;
    @Size(max = 50)
    private String status;
    @Size(max = 100)
    private String reason_code;
    private LocalDateTime created_at;
}
