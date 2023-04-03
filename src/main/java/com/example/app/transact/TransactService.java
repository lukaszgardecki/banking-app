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
        else if (transactForm instanceof WithdrawTransactForm) checkWithdrawTransactForm(transactForm);
    }

    private void checkDepositTransactForm(TransactForm transactForm) {
        String amount = transactForm.getAmount();
        String accountTo = ((DepositTransactForm) transactForm).getAccountTo();
        if (amount.isEmpty() && accountTo.isEmpty()) throw new EmptyFieldException(Message.MISSING_FIELDS);
        if (amount.isEmpty()) throw new EmptyFieldException(Message.DEPOSIT_AMOUNT_FIELD_EMPTY);
        if (accountTo.isEmpty()) throw new EmptyFieldException(Message.DEPOSIT_ACCOUNT_TO_EMPTY);
        checkAmountField(amount);
    }

    private void checkTransferTransactForm(TransactForm transactForm) {
        String amount = transactForm.getAmount();
        String accountFrom = ((TransferTransactForm) transactForm).getAccountFrom();
        String accountTo = ((TransferTransactForm) transactForm).getAccountTo();
        if ((amount.isEmpty() && accountFrom.isEmpty() && accountTo.isEmpty())
                || amount.isEmpty() && accountFrom.isEmpty()
                || accountFrom.isEmpty() && accountTo.isEmpty()
                || amount.isEmpty() && accountTo.isEmpty()
        ) throw new EmptyFieldException(Message.MISSING_FIELDS);
        if (accountFrom.isEmpty()) throw new EmptyFieldException(Message.TRANSFER_ACCOUNT_FROM_EMPTY);
        if (accountTo.isEmpty()) throw new EmptyFieldException(Message.TRANSFER_ACCOUNT_TO_EMPTY);
        if (amount.isEmpty()) throw new EmptyFieldException(Message.TRANSFER_AMOUNT_FIELD_EMPTY);
        checkAmountField(amount);
        checkIfAccountAreTheSame(accountFrom, accountTo);
    }

    private void checkWithdrawTransactForm(TransactForm transactForm) {
        String amount = transactForm.getAmount();
        String accountTo = ((WithdrawTransactForm) transactForm).getAccountFrom();
        if (amount.isEmpty() && accountTo.isEmpty()) throw new EmptyFieldException(Message.MISSING_FIELDS);
        if (amount.isEmpty()) throw new EmptyFieldException(Message.WITHDRAW_AMOUNT_FIELD_EMPTY);
        if (accountTo.isEmpty()) throw new EmptyFieldException(Message.WITHDRAW_ACCOUNT_FROM_EMPTY);
        checkAmountField(amount);
    }

    private void checkIfAccountAreTheSame(String accountFrom, String accountTo) {
        if (accountFrom.equals(accountTo)) throw new SameAccountsFieldsException(Message.TRANSFER_ACCOUNTS_ARE_SAME);
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
