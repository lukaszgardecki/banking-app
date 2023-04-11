package com.example.app.account;

import com.example.app.account.dto.AccountDashboardDto;
import com.example.app.account.dto.AccountDto;
import com.example.app.account.mappers.AccountDashboardMapper;
import com.example.app.account.mappers.AccountDtoMapper;
import com.example.app.exceptions.transact.TooLowBalanceException;
import com.example.app.helpers.AccountNumGenerator;
import com.example.app.helpers.Message;
import com.example.app.transact.TransactDto;
import com.example.app.transact.forms.TransactForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                .collect(Collectors.toCollection(ArrayList::new));
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

    public TransactDto depositMoney(TransactForm form) {
        return deposit(form);
    }

    public TransactDto withdrawMoney(TransactForm form) {
        return withdraw(form);
    }

    public Stream<TransactDto> transferMoney(TransactForm form) {
        TransactDto withdrawTransact = withdraw(form);
        TransactDto depositTransact = deposit(form);
        return Stream.of(depositTransact, withdrawTransact);
    }

    private Account getAccountToRegister(AccountDto account) {
        BigDecimal newId = accountRepository.getMaxId()
                .map(id -> id.add(BigDecimal.ONE))
                .orElse(BigDecimal.ONE);
        String accountNumber = AccountNumGenerator.generate(newId);
        account.setBalance(new BigDecimal("0.00"));
        account.setAccount_number(accountNumber);
        account.setCreated_at(LocalDateTime.now());
        return AccountDtoMapper.map(account);
    }

    private TransactDto deposit(TransactForm form) {
        BigDecimal amount = new BigDecimal(form.getAmount());
        Long accountToId = Long.parseLong(form.getAccountToId());
        BigDecimal accountToBalance = getAccountBalance(accountToId);
        BigDecimal newBalance = accountToBalance.add(amount);
        TransactDto transact = updateAccountBalance(newBalance, accountToId);
        transact.setTransactionType(Message.DEPOSIT_TRANSACTION_TYPE);
        return transact;
    }

    private TransactDto withdraw(TransactForm form) {
        BigDecimal amount = new BigDecimal(form.getAmount());
        Long accountFromId = Long.parseLong(form.getAccountFromId());
        BigDecimal accountFromBalance = getAccountBalance(accountFromId);
        checkIfAccountHasFunds(amount, accountFromBalance);
        BigDecimal newAccountFromBalance = accountFromBalance.subtract(amount);
        TransactDto transact = updateAccountBalance(newAccountFromBalance, accountFromId);
        transact.setTransactionType(Message.WITHDRAW_TRANSACTION_TYPE);
        return transact;
    }

    private static void checkIfAccountHasFunds(BigDecimal amount, BigDecimal accountFromBalance) {
        int i = accountFromBalance.compareTo(amount);
        if (i < 0) throw new TooLowBalanceException(Message.TOO_LOW_BALANCE);
    }
}
