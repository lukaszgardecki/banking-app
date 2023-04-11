package com.example.app.transact;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_history")
@Getter
@Setter
public class Transact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long account_id;
    @Size(max = 50)
    private String transaction_type;
    private BigDecimal amount;
    @Size(max = 10)
    private String currency;
    @Size(max = 50)
    private String source;
    @Size(max = 50)
    private String status;
    @Size(max = 100)
    private String reason_code;
    private LocalDateTime created_at;

    public void setAmount(String amount) {
        this.amount = new BigDecimal(amount);
    }
}
