package com.example.app.account.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AccountDto {
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

    public AccountDto() {}

    public AccountDto(Long userId, String accountName, String accountType) {
        this.user_id = userId;
        this.account_name = accountName;
        this.account_type = accountType;
    }
}
