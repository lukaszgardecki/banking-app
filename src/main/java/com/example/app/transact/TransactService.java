package com.example.app.transact;

import com.example.app.exceptions.form.EmptyFieldException;
import com.example.app.exceptions.form.SameAccountsFieldsException;
import com.example.app.helpers.Message;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class TransactService {

    public void validateForm(TransactForm transactForm) {
        transactForm.setAmount(transactForm.getAmount().replaceAll(",", "."));
        checkEmptyFields(transactForm);
        checkAmountField(transactForm);
        if (transactForm instanceof TransferTransactForm) {
            checkIfAccountsAreTheSame((TransferTransactForm) transactForm);
        }
    }

    private void checkEmptyFields(TransactForm transactForm) {
            // TODO: 03.04.2023 może być błąd dla PAYMENTS bo reference MOŻE być puste, a couter liczy wszystkie puste pola
        long amountOfEmptyFields = countEmptyFields(transactForm, transactForm.getClass());
        System.out.println("Ilość pustych pól?: " + amountOfEmptyFields);
        if (amountOfEmptyFields > 1) throw new EmptyFieldException(Message.MISSING_FIELDS);
        String amount = transactForm.getAmount();

        if (transactForm instanceof DepositTransactForm) {
            String accountTo = transactForm.getAccount();
            if (amount.isEmpty()) throw new EmptyFieldException(Message.DEPOSIT_AMOUNT_FIELD_EMPTY);
            if (accountTo.isEmpty()) throw new EmptyFieldException(Message.DEPOSIT_ACCOUNT_TO_EMPTY);
        } else if (transactForm instanceof WithdrawTransactForm) {
            String accountFrom = transactForm.getAccount();
            if (amount.isEmpty()) throw new EmptyFieldException(Message.WITHDRAW_AMOUNT_FIELD_EMPTY);
            if (accountFrom.isEmpty()) throw new EmptyFieldException(Message.WITHDRAW_ACCOUNT_FROM_EMPTY);
        } else if (transactForm instanceof TransferTransactForm) {
            String accountFrom = ((TransferTransactForm) transactForm).getAccount();
            String accountTo = ((TransferTransactForm) transactForm).getAccountTo();
            if (accountFrom.isEmpty()) throw new EmptyFieldException(Message.TRANSFER_ACCOUNT_FROM_EMPTY);
            if (accountTo.isEmpty()) throw new EmptyFieldException(Message.TRANSFER_ACCOUNT_TO_EMPTY);
            if (amount.isEmpty()) throw new EmptyFieldException(Message.TRANSFER_AMOUNT_FIELD_EMPTY);
        } else if (transactForm instanceof PaymentTransactForm) {
            String beneficiary = ((PaymentTransactForm) transactForm).getBeneficiary();
            String accountNumber = ((PaymentTransactForm) transactForm).getAccountNumber();
            String accountFrom = ((PaymentTransactForm) transactForm).getAccount();
            if (amount.isEmpty()) throw new EmptyFieldException(Message.PAYMENT_AMOUNT_FIELD_EMPTY);
            if (beneficiary.isEmpty()) throw new EmptyFieldException(Message.PAYMENT_BENEFICIARY_EMPTY);
            if (accountNumber.isEmpty()) throw new EmptyFieldException(Message.PAYMENT_ACCOUNT_NO_EMPTY);
            if (accountFrom.isEmpty()) throw new EmptyFieldException(Message.PAYMENT_ACCOUNT_FROM_EMPTY);
        }
    }

    private long countEmptyFields(TransactForm transactForm, Class<?> clazz) {
        if (clazz == null) return 0;
        Field[] mainClassFields = clazz.getDeclaredFields();
        return Arrays.stream(mainClassFields).filter(field -> {
            try {
                field.setAccessible(true);
                String value = field.get(transactForm).toString();
                return value.isEmpty();
            } catch (IllegalAccessException | IllegalArgumentException e) {
                throw new RuntimeException(e);
            }
        }).count() + countEmptyFields(transactForm, clazz.getSuperclass());
    }

    private void checkIfAccountsAreTheSame(TransferTransactForm transactForm) {
        String accountFrom = transactForm.getAccount();
        String accountTo = transactForm.getAccountTo();
        if (accountFrom.equals(accountTo)) throw new SameAccountsFieldsException(Message.TRANSFER_ACCOUNTS_ARE_SAME);
    }

    private void checkAmountField(TransactForm transactForm) throws NumberFormatException {
        String amount = transactForm.getAmount();
        try {
            int amountValue = new BigDecimal(amount).compareTo(BigDecimal.ZERO);
            if (amountValue == 0 || amountValue < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            throw new NumberFormatException(Message.INCORRECT_AMOUNT);
        }
    }
}
