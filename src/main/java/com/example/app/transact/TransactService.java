package com.example.app.transact;

import com.example.app.exceptions.form.EmptyFieldException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactService {
    public static final String EMPTY_FIELD_MESSAGE = "Complete the missing fields";
    public static final String AMOUNT_EMPTY_MESSAGE = "Deposit amount cannot be empty";
    public static final String ACCOUNT_DEPOSITING_TO_EMPTY_MESSAGE = "Account depositing to cannot be empty";
    public static final String BAD_DATA_INPUT_MESSAGE = "Please enter the correct amount";

    public void validateForm(TransactForm transactForm) {
        if (transactForm instanceof DepositTransactForm) checkDepositTransactForm(transactForm);
        else if (transactForm instanceof TransferTransactForm) checkTransferTransactForm(transactForm);
    }

    private void checkDepositTransactForm(TransactForm transactForm) {
        String amount = transactForm.getAmount();
        String account = ((DepositTransactForm) transactForm).getAccountTo();
        if (amount.isEmpty() && account.isEmpty()) throw new EmptyFieldException(EMPTY_FIELD_MESSAGE);
        if (amount.isEmpty()) throw new EmptyFieldException(AMOUNT_EMPTY_MESSAGE);
        if (account.isEmpty()) throw new EmptyFieldException(ACCOUNT_DEPOSITING_TO_EMPTY_MESSAGE);
        checkAmountField(amount);
    }


    private void checkAmountField(String amount) throws NumberFormatException {
        try {
            int amountValue = new BigDecimal(amount).compareTo(BigDecimal.ZERO);
            if (amountValue == 0 || amountValue < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            throw new NumberFormatException(BAD_DATA_INPUT_MESSAGE);
        }
    }
}
