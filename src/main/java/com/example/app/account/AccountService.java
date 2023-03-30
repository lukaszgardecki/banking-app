package com.example.app.account;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public List<Account> getAccountsByUserId(Long id) {
        return accountRepository.findAccountsByUser_id(id);
    }

    public BigDecimal getTotalBalance(Long user_id) {
        return accountRepository.getTotalBalance(user_id);
    }
}
