package com.example.app.transact;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "v_transaction_history")
@Getter
@Setter
public class TransactHistory {
    @Id
    private Long transactionId;
    private Long accountId;
    private String accountName;
    private Long userId;
    @Size(max = 50)
    private String transactionType;
    private BigDecimal amount;
    @Size(max = 50)
    private String source;
    @Size(max = 50)
    private String status;
    @Size(max = 100)
    private String reasonCode;
    private LocalDateTime createdAt;
}
