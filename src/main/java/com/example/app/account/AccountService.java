package com.example.app.account;

import com.example.app.account.dto.AccountDashboardDto;
import com.example.app.account.mappers.AccountDashboardMapper;
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

    @Transactional
    public void changeAccountBalance(BigDecimal newBalance, Long accountId) {
        accountRepository.changeAccountBalanceById(newBalance, accountId);
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
}
