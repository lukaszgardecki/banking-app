package com.example.app.account.mappers;

import com.example.app.account.Account;
import com.example.app.account.dto.AccountDashboardDto;
import com.example.app.account.dto.AccountDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AccountDtoMapperTest {

    @Test
    void shouldMapAccountDtoToAccount() {
        AccountDto dto = getTestAccountDto();
        Account account = AccountDtoMapper.map(dto);
        assertEquals(dto.getUser_id(), account.getUser_id());
        assertEquals(dto.getAccount_number(), account.getAccount_number());
        assertEquals(dto.getAccount_name(), account.getAccount_name());
        assertEquals(dto.getAccount_type(), account.getAccount_type());
        assertEquals(dto.getBalance(), account.getBalance());
        assertEquals(dto.getCreated_at(), account.getCreated_at());
    }

    @Test
    void shouldMapAccountToAccountDto() {
        Account account = getTestAccount();
        AccountDto dto = AccountDtoMapper.map(account);
        assertEquals(dto.getUser_id(), account.getUser_id());
        assertEquals(dto.getAccount_number(), account.getAccount_number());
        assertEquals(dto.getAccount_name(), account.getAccount_name());
        assertEquals(dto.getAccount_type(), account.getAccount_type());
        assertEquals(dto.getBalance(), account.getBalance());
        assertEquals(dto.getCreated_at(), account.getCreated_at());
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

    private AccountDto getTestAccountDto() {
        AccountDto account = new AccountDto();
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