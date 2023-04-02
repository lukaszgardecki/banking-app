package com.example.app.transact;

import com.example.app.account.AccountRepository;
import com.example.app.exceptions.form.EmptyFieldException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransactService {
    public static final String EMPTY_FIELD_MESSAGE = "Complete the missing fields";
    public static final String AMOUNT_EMPTY_MESSAGE = "Deposit amount cannot be empty";
    public static final String ACCOUNT_DEPOSITING_TO_EMPTY_MESSAGE = "Account depositing to cannot be empty";
    public static final String BAD_DATA_INPUT_MESSAGE = "Please enter the correct amount";
    private final AccountRepository accountRepository;

    public TransactService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void changeAccountBalance(BigDecimal newBalance, Long accountId) {
        accountRepository.changeAccountBalanceById(newBalance, accountId);
    }


    public void validateValues(String depositAmountStr, String accountId) {
        if (depositAmountStr.isEmpty() && accountId.isEmpty()) throw new EmptyFieldException(EMPTY_FIELD_MESSAGE);
        if (depositAmountStr.isEmpty()) throw new EmptyFieldException(AMOUNT_EMPTY_MESSAGE);
        if (accountId.isEmpty()) throw new EmptyFieldException(ACCOUNT_DEPOSITING_TO_EMPTY_MESSAGE);
        try {
            int amountValue = new BigDecimal(depositAmountStr).compareTo(BigDecimal.ZERO);
            if (amountValue == 0 || amountValue < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            throw new NumberFormatException(BAD_DATA_INPUT_MESSAGE);
        }
    }
}
