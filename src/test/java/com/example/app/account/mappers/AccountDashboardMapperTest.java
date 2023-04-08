package com.example.app.account.mappers;

import com.example.app.account.Account;
import com.example.app.account.dto.AccountDashboardDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AccountDashboardMapperTest {

    @Test
    void shouldMapAccountToAccountDashboardDto() {
        Account account = getTestAccount();
        AccountDashboardDto dto = AccountDashboardMapper.map(account);
        assertEquals(dto.getId(), account.getId());
        assertEquals(dto.getAccountNumber(), account.getAccount_number());
        assertEquals(dto.getAccountName(), account.getAccount_name());
        assertEquals(dto.getAccountType(), account.getAccount_type());
        assertEquals(dto.getBalance(), account.getBalance());
        assertEquals(dto.getCreatedAt(), account.getCreated_at());
    }

    private Account getTestAccount() {
        Account account = new Account();
        account.setId(1L);
        account.setUser_id(5L);
        account.setAccount_number("777");
        account.setAccount_name("Konto");
        account.setAccount_type("check");
        account.setBalance(new BigDecimal("123"));
        account.setCreated_at(LocalDateTime.now());
        account.setUpdated_at(LocalDateTime.now());
        return account;
    }
}