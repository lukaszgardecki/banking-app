package com.example.app.account;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @Size(max = 100)
    private String account_number;
    @Size(max = 50)
    private String account_name;
    @Size(max = 50)
    private String account_type;
    private BigDecimal balance;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
