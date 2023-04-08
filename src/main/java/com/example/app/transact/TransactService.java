package com.example.app.transact;

import com.example.app.exceptions.form.EmptyFieldException;
import com.example.app.exceptions.form.SameAccountsFieldsException;
import com.example.app.helpers.Message;
import com.example.app.transact.forms.*;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class TransactService {
    private final TransactRepository transactRepository;
    private final TransactHistoryRepository transactHistoryRepository;

    public TransactService(TransactRepository transactRepository, TransactHistoryRepository transactHistoryRepository) {
        this.transactRepository = transactRepository;
        this.transactHistoryRepository = transactHistoryRepository;
    }

    public void logTransaction(TransactDto transact) {
        Transact transactToSave = TransactDtoMapper.map(transact);
        transactRepository.save(transactToSave);
    }

    public List<TransactHistory> getTransactionHistoryByUserId(Long userId) {
        return transactHistoryRepository.findAllByUserId(userId);
    }

    public void validateForm(TransactForm transactForm) {
        String amount = transactForm.getAmount().replaceAll(",", ".");
        transactForm.setAmount(amount);
        checkEmptyFields(transactForm);
        checkAmountField(transactForm);
        if (transactForm instanceof TransferTransactForm) {
            checkIfAccountsAreTheSame((TransferTransactForm) transactForm);
        }
    }

    public void prepareSuccessTransact(TransactDto transact, TransactForm form) {

        if (form instanceof DepositTransactForm) {
            transact.setReasonCode(Message.DEPOSIT_REASON_CODE_SUCCESS);
            transact.setTransactionType(Message.DEPOSIT_TRANSACTION_TYPE);
            transact.setAccountId(Long.parseLong(form.getAccountToId()));
            transact.setAmount("+" + form.getAmount());
        } else if (form instanceof WithdrawTransactForm) {
            transact.setReasonCode(Message.WITHDRAW_REASON_CODE_SUCCESS);
            transact.setTransactionType(Message.WITHDRAW_TRANSACTION_TYPE);
            transact.setAccountId(Long.parseLong(form.getAccountFromId()));
            transact.setAmount("-" + form.getAmount());
        } else if (form instanceof TransferTransactForm) {
            transact.setReasonCode(Message.TRANSFER_REASON_CODE_SUCCESS);
            switch (transact.getTransactionType()) {
                case Message.WITHDRAW_TRANSACTION_TYPE -> {
                    transact.setAccountId(Long.parseLong(form.getAccountFromId()));
                    transact.setAmount("-" + form.getAmount());
                }
                case Message.DEPOSIT_TRANSACTION_TYPE -> {
                    transact.setAccountId(Long.parseLong(form.getAccountToId()));
                    transact.setAmount("+" + form.getAmount());
                }
            }
            transact.setTransactionType(Message.TRANSFER_TRANSACTION_TYPE);
        } else if (form instanceof PaymentTransactForm) {
            transact.setReasonCode(Message.PAYMENT_REASON_CODE_SUCCESS);
            transact.setTransactionType(Message.PAYMENT_TRANSACTION_TYPE);
            transact.setAccountId(Long.parseLong(form.getAccountFromId()));
            transact.setAmount("-" + form.getAmount());
        }
        transact.setSource(Message.SOURCE);
        transact.setStatus(Message.STATUS_SUCCESS);
    }

    public void prepareFailedTransact(TransactDto transact, TransactForm form) {
         if (form instanceof WithdrawTransactForm) {
            transact.setTransactionType(Message.WITHDRAW_TRANSACTION_TYPE);
        } else if (form instanceof TransferTransactForm) {
             transact.setTransactionType(Message.TRANSFER_TRANSACTION_TYPE);
        } else if (form instanceof PaymentTransactForm) {
             transact.setTransactionType(Message.PAYMENT_TRANSACTION_TYPE);
         }
        transact.setAccountId(Long.parseLong(form.getAccountFromId()));
        transact.setAmount(form.getAmount());
        transact.setSource(Message.SOURCE);
        transact.setStatus(Message.STATUS_FAILED);
        transact.setReasonCode(Message.WITHDRAW_REASON_CODE_FAILURE);
        transact.setCreatedAt(LocalDateTime.now());
    }

    private void checkEmptyFields(TransactForm form) {
        long amountOfEmptyFields = countEmptyFields(form);
        if (amountOfEmptyFields > 1) throw new EmptyFieldException(Message.MISSING_FIELDS);
        String amount = form.getAmount();

        if (form instanceof DepositTransactForm) {
            String accountTo = form.getAccountToId();
            if (amount.isEmpty()) throw new EmptyFieldException(Message.DEPOSIT_AMOUNT_FIELD_EMPTY);
            if (accountTo.isEmpty()) throw new EmptyFieldException(Message.DEPOSIT_ACCOUNT_TO_EMPTY);
        } else if (form instanceof WithdrawTransactForm) {
            String accountFrom = form.getAccountFromId();
            if (amount.isEmpty()) throw new EmptyFieldException(Message.WITHDRAW_AMOUNT_FIELD_EMPTY);
            if (accountFrom.isEmpty()) throw new EmptyFieldException(Message.WITHDRAW_ACCOUNT_FROM_EMPTY);
        } else if (form instanceof TransferTransactForm) {
            String accountFrom = form.getAccountFromId();
            String accountTo = form.getAccountToId();
            if (accountFrom.isEmpty()) throw new EmptyFieldException(Message.TRANSFER_ACCOUNT_FROM_EMPTY);
            if (accountTo.isEmpty()) throw new EmptyFieldException(Message.TRANSFER_ACCOUNT_TO_EMPTY);
            if (amount.isEmpty()) throw new EmptyFieldException(Message.TRANSFER_AMOUNT_FIELD_EMPTY);
        } else if (form instanceof PaymentTransactForm) {
            String beneficiary = form.getBeneficiary();
            String accountNumber = form.getAccountToNumber();
            String accountFrom = form.getAccountFromId();
            if (amount.isEmpty()) throw new EmptyFieldException(Message.PAYMENT_AMOUNT_FIELD_EMPTY);
            if (beneficiary.isEmpty()) throw new EmptyFieldException(Message.PAYMENT_BENEFICIARY_EMPTY);
            if (accountNumber.isEmpty()) throw new EmptyFieldException(Message.PAYMENT_ACCOUNT_NO_EMPTY);
            if (accountFrom.isEmpty()) throw new EmptyFieldException(Message.PAYMENT_ACCOUNT_FROM_EMPTY);
        }
    }

    private long countEmptyFields(TransactForm transactForm) {
        Field[] mainClassFields = transactForm.getClass().getDeclaredFields();

        return Arrays.stream(mainClassFields).filter(field -> {
            try {
                field.setAccessible(true);
                if (field.get(transactForm) == null) return true;
                String value = field.get(transactForm).toString();
                return value.isEmpty();
            } catch (IllegalAccessException | IllegalArgumentException e) {
                throw new RuntimeException(e);
            }
        }).count();
    }

    private void checkIfAccountsAreTheSame(TransferTransactForm transactForm) {
        String accountFrom = transactForm.getAccountFromId();
        String accountTo = transactForm.getAccountToId();
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
