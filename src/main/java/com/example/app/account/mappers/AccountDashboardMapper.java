package com.example.app.account.mappers;

import com.example.app.account.Account;
import com.example.app.account.dto.AccountDashboardDto;

public class AccountDashboardMapper {

    public static AccountDashboardDto map(Account account) {
        AccountDashboardDto dto = new AccountDashboardDto();
        dto.setAccountNumber(account.getAccount_number());
        dto.setAccountName(account.getAccount_name());
        dto.setAccountType(account.getAccount_type());
        dto.setBalance(account.getBalance());
        return dto;
    }
}
