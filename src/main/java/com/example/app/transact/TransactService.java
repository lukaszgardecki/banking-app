package com.example.app.transact;

import com.example.app.exceptions.form.EmptyFieldException;
import com.example.app.exceptions.form.SameAccountsFieldsException;
import com.example.app.helpers.Message;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactService {

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
            throw new NumberFormatException(Message.INCORRECT_AMOUNT);
        }
    }
}
