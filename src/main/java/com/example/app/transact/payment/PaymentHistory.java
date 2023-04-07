package com.example.app.transact.payment;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    private Long id;
    private Long accountFromId;
    @Size(max = 50)
    private String beneficiary;
    @Size(max = 255)
    private String beneficiaryAccNo;
    private BigDecimal amount;
    @Size(max = 255)
    private String reference;
    @Size(max = 50)
    private String status;
    @Size(max = 100)
    private String reasonCode;
    private LocalDateTime createdAt;
}
