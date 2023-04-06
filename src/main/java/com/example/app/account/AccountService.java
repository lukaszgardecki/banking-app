package com.example.app.account;

import com.example.app.account.dto.AccountDashboardDto;
import com.example.app.account.mappers.AccountDashboardMapper;
import com.example.app.exceptions.transact.TooLowBalanceException;
import com.example.app.helpers.AccountNumGenerator;
import com.example.app.helpers.Message;
import com.example.app.transact.forms.TransactForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountDashboardDto> getAccountsByUserId(Long id) {
        return accountRepository.findAccountsByUserId(id)
                .stream()
                .map(AccountDashboardMapper::map)
                .toList();
    }

    @Transactional
    public void createAccount(AccountDto account) {
        Account accountToSave = getAccountToRegister(account);
        System.out.println(accountToSave);
        accountRepository.save(accountToSave);
    }

    @Transactional
    public TransactDto updateAccountBalance(BigDecimal newBalance, Long accountId) {
        LocalDateTime currentTime = LocalDateTime.now();
        Account account = accountRepository.findById(accountId).orElseThrow();
        account.setBalance(newBalance);
        account.setUpdated_at(currentTime);
        accountRepository.save(account);

        TransactDto transact = new TransactDto();
        transact.setCreatedAt(currentTime);
        return transact;
    }

    public BigDecimal getAccountBalance(Long accountId) {
        return accountRepository.findById(accountId)
                .map(Account::getBalance)
                .get();
    }

    public void depositMoney(TransactForm form) {
        deposit(form);
    }

    public void withdrawMoney(TransactForm form) {
        withdraw(form);
    }

    public void transferMoney(TransactForm form) {
        withdraw(form);
        deposit(form);
    }

    private String generateAccountNumber() {
        BigDecimal newId = accountRepository.getMaxId()
                .map(id -> id.add(BigDecimal.ONE))
                .orElse(BigDecimal.ONE);
        String accountNumber = AccountNumGenerator.generate(newId);
        account.setBalance(new BigDecimal("0.00"));
        account.setAccount_number(accountNumber);
        account.setCreated_at(LocalDateTime.now());
        return AccountDtoMapper.map(account);
    }

    private void deposit(TransactForm form) {
        String amount = form.getAmount();
        String accountTo = form.getAccount();

        BigDecimal depositAmount = new BigDecimal(amount);
        BigDecimal accountToBalance = getAccountBalance(accountTo);
        BigDecimal newBalance = accountToBalance.add(depositAmount);
        changeAccountBalance(newBalance, Long.parseLong(accountTo));
    }

    private void withdraw(TransactForm form) {
        String amount = form.getAmount();
        String accountFrom = form.getAccount();

        BigDecimal accountFromBalance = getAccountBalance(accountFrom);
        checkIfAccountHasFunds(amount, accountFromBalance);
        BigDecimal withdrawAmount = new BigDecimal(amount);
        BigDecimal newAccountFromBalance = accountFromBalance.subtract(withdrawAmount);
        changeAccountBalance(newAccountFromBalance, Long.parseLong(accountFrom));
    }

    private static void checkIfAccountHasFunds(BigDecimal amount, BigDecimal accountFromBalance) {
        int i = accountFromBalance.compareTo(amount);
        if (i < 0) throw new TooLowBalanceException(Message.TOO_LOW_BALANCE);
    }
}
