package com.example.app.account;

import com.example.app.account.dto.AccountDashboardDto;
import com.example.app.account.mappers.AccountDashboardMapper;
import com.example.app.exceptions.transact.TooLowBalanceException;
import com.example.app.helpers.Message;
import com.example.app.transact.DepositTransactForm;
import com.example.app.transact.TransferTransactForm;
import com.example.app.transact.WithdrawTransactForm;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {
    public static final int BANK_NUM = 12772055;
    public static final int USER_NUM_LEN = 16;
    public static final int CONTROL_SUM_LEN = 2;
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
    public void createAccount(Long userId, String accountNumber, String accountName, String accountType) {
        accountRepository.createAccount(userId, accountNumber, accountName, accountType, LocalDateTime.now());
    }

    public String generateAccountNumber() {
        BigDecimal newId = accountRepository.getMaxId()
                                .map(id -> id.add(BigDecimal.ONE))
                                .orElse(BigDecimal.ONE);
        String userNumber = createUserNum(newId);
        String controlSum = getControlSum(userNumber);
        return controlSum + BANK_NUM + userNumber;
    }


    public void changeAccountBalance(BigDecimal newBalance, Long accountId) {
        accountRepository.changeAccountBalanceById(newBalance, accountId);
    }

    public BigDecimal getAccountBalance(String accountId, List<AccountDashboardDto> userAccounts) {
        return userAccounts.stream()
                .filter(acc -> String.valueOf(acc.getId()).equals(accountId))
                .map(AccountDashboardDto::getBalance)
                .findFirst().get();
    }

    public void doDeposit(DepositTransactForm form, HttpSession session) {
        String amount = form.getAmount();
        String accountTo = form.getAccountTo();

        BigDecimal depositAmount = new BigDecimal(amount);
        List<AccountDashboardDto> userAccounts = (List<AccountDashboardDto>) session.getAttribute("userAccounts");
        BigDecimal accountToBalance = getAccountBalance(accountTo, userAccounts);
        BigDecimal newBalance = accountToBalance.add(depositAmount);
        changeAccountBalance(newBalance, Long.parseLong(accountTo));
    }

    public void doTransfer(TransferTransactForm form, HttpSession session) {
        String amount = form.getAmount();
        String accountFrom = form.getAccountFrom();
        String accountTo = form.getAccountTo();

        List<AccountDashboardDto> userAccounts = (List<AccountDashboardDto>) session.getAttribute("userAccounts");
        BigDecimal accountFromBalance = getAccountBalance(accountFrom, userAccounts);

        checkIfAccountHasFunds(amount, accountFromBalance);

        BigDecimal transferAmount = new BigDecimal(amount);
        BigDecimal accountToBalance = getAccountBalance(accountTo, userAccounts);

        BigDecimal newAccountFromBalance = accountFromBalance.subtract(transferAmount);
        BigDecimal newAccountToBalance = accountToBalance.add(transferAmount);

        changeAccountBalance(newAccountFromBalance, Long.parseLong(accountFrom));
        changeAccountBalance(newAccountToBalance, Long.parseLong(accountTo));
    }

    public void doWithdraw(WithdrawTransactForm form, HttpSession session) {
        String amount = form.getAmount();
        String accountFrom = form.getAccountFrom();

        List<AccountDashboardDto> userAccounts = (List<AccountDashboardDto>) session.getAttribute("userAccounts");
        BigDecimal accountFromBalance = getAccountBalance(accountFrom, userAccounts);

        checkIfAccountHasFunds(amount, accountFromBalance);

        BigDecimal withdrawAmount = new BigDecimal(amount);
        BigDecimal newAccountFromBalance = accountFromBalance.subtract(withdrawAmount);
        changeAccountBalance(newAccountFromBalance, Long.parseLong(accountFrom));
    }

    private String getControlSum(String userNum) {
        String preAccNum = BANK_NUM + userNum;
        while (preAccNum.length() != CONTROL_SUM_LEN) {
            preAccNum = doSumLoop(preAccNum);
        }
        return preAccNum;
    }

    private String doSumLoop(String num) {
        int increasingParam = 3;
        int sum = 0;

        for (int i = 0; i < num.length(); i++) {
            String digitStr = Character.toString(num.charAt(i));
            int digitInt = Integer.parseInt(digitStr);
            sum += (digitInt * increasingParam);
        }
        return String.valueOf(sum);
    }
    private String createUserNum(BigDecimal maxAccountId) {
        int amountOfZeros = USER_NUM_LEN - maxAccountId.toString().length();
        String leadZeros = "0".repeat(amountOfZeros);
        return leadZeros + maxAccountId;
    }

    private static void checkIfAccountHasFunds(String amount, BigDecimal accountFromBalance) {
        int i = accountFromBalance.compareTo(new BigDecimal(amount));
        if (i < 0) throw new TooLowBalanceException(Message.TOO_LOW_BALANCE);
    }
}
