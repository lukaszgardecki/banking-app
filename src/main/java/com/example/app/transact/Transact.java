package com.example.app.transact;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Transact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long account_id;
    @Max(50)
    private String transaction_type;
    private BigDecimal amount;
    @Max(50)
    private String source;
    @Max(50)
    private String status;
    @Max(100)
    private String reason_code;
    private LocalDateTime created_at;
}
