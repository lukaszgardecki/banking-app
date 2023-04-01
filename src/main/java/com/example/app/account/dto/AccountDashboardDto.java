package com.example.app.account.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AccountDashboardDto {
    private Long id;
    private String accountNumber;
    private String accountName;
    private String accountType;
    private BigDecimal balance;
    private LocalDateTime createdAt;
}
