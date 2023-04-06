package com.example.app.account.mappers;

import com.example.app.account.Account;
import com.example.app.account.dto.AccountDto;

public class AccountDtoMapper {

    public static Account map(AccountDto dto) {
        Account account = new Account();
        account.setUser_id(dto.getUser_id());
        account.setAccount_number(dto.getAccount_number());
        account.setAccount_name(dto.getAccount_name());
        account.setAccount_type(dto.getAccount_type());
        account.setBalance(dto.getBalance());
        account.setCreated_at(dto.getCreated_at());
        account.setUpdated_at(dto.getUpdated_at());
        return account;
    }

    public static AccountDto map(Account account) {
        AccountDto dto = new AccountDto();
        dto.setUser_id(account.getUser_id());
        dto.setAccount_number(account.getAccount_number());
        dto.setAccount_name(account.getAccount_name());
        dto.setAccount_type(account.getAccount_type());
        dto.setBalance(account.getBalance());
        dto.setCreated_at(account.getCreated_at());
        dto.setUpdated_at(account.getUpdated_at());
        return dto;
    }
}
