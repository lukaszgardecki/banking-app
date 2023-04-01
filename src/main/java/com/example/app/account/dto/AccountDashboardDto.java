package com.example.app.account.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountDashboardDto {
    private String accountNumber;
    private String accountName;
    private String accountType;
    private BigDecimal balance;
}
