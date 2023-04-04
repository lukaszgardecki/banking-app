package com.example.app.account;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user_id;
    @Max(100)
    private String account_number;
    @Max(50)
    private String account_name;
    @Max(50)
    private String account_type;
    private BigDecimal balance;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
