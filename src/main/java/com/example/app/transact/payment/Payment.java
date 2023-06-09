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
    private Long accountFromId;
    private Long transactId;
    @Size(max = 255)
    private String beneficiary;
    @Size(max = 255)
    private String beneficiaryAccNo;
    private BigDecimal amount;
    @Size(max = 10)
    private String currency;
    @Size(max = 255)
    private String reference;
    @Size(max = 50)
    private String status;
    @Size(max = 100)
    private String reasonCode;
    private LocalDateTime createdAt;
}
