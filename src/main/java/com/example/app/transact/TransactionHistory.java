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
public class TransactionHistory {
    @Id
    private Long transaction_id;
    private Long account_id;
    private Long user_id;
    @Size(max = 50)
    private String transaction_type;
    private BigDecimal amount;
    @Size(max = 50)
    private String source;
    @Size(max = 50)
    private String status;
    @Size(max = 100)
    private String reason_code;
    private LocalDateTime created_at;

}
