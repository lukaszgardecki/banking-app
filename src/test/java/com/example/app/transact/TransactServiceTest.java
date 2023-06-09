package com.example.app.transact;

import com.example.app.exceptions.form.EmptyFieldException;
import com.example.app.exceptions.form.SameAccountsFieldsException;
import com.example.app.helpers.Message;
import com.example.app.transact.forms.DepositTransactForm;
import com.example.app.transact.forms.TransactForm;
import com.example.app.transact.forms.TransferTransactForm;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactServiceTest {
    TransactRepository transactRepository = Mockito.mock(TransactRepository.class);
    TransactHistoryRepository transactHistoryRepository = Mockito.mock(TransactHistoryRepository.class);
    private final TransactService transactService = new TransactService(transactRepository, transactHistoryRepository);

    @ParameterizedTest
    @MethodSource
    void shouldReturnCorrectMessage(TransactForm transactForm, String expected) {
        String message = "";
        try {
            transactService.validateForm(transactForm);
        } catch (EmptyFieldException | NumberFormatException | SameAccountsFieldsException e) {
            message = e.getMessage();
        }
        assertEquals(expected, message);
    }

    private static Stream<Arguments> shouldReturnCorrectMessage() {
        return Stream.of(
                Arguments.of(new DepositTransactForm("", ""), Message.MISSING_FIELDS),
                Arguments.of(new DepositTransactForm("", "0000"), Message.DEPOSIT_AMOUNT_FIELD_EMPTY),
                Arguments.of(new DepositTransactForm("123", ""), Message.DEPOSIT_ACCOUNT_TO_EMPTY),
                Arguments.of(new DepositTransactForm("badData", "0000"), Message.INCORRECT_AMOUNT),
                Arguments.of(new DepositTransactForm("/", "0000"), Message.INCORRECT_AMOUNT),
                Arguments.of(new DepositTransactForm("0", "0000"), Message.INCORRECT_AMOUNT),
                Arguments.of(new DepositTransactForm("-1", "0000"), Message.INCORRECT_AMOUNT),
                Arguments.of(new DepositTransactForm("-1000", "0000"), Message.INCORRECT_AMOUNT),

                Arguments.of(new TransferTransactForm("", "", ""), Message.MISSING_FIELDS),
                Arguments.of(new TransferTransactForm("123", "", ""), Message.MISSING_FIELDS),
                Arguments.of(new TransferTransactForm("", "0000", ""), Message.MISSING_FIELDS),
                Arguments.of(new TransferTransactForm("", "", "0000"), Message.MISSING_FIELDS),
                Arguments.of(new TransferTransactForm("", "0000", "1111"), Message.TRANSFER_AMOUNT_FIELD_EMPTY),
                Arguments.of(new TransferTransactForm("badData", "0000", "1111"), Message.INCORRECT_AMOUNT),
                Arguments.of(new TransferTransactForm("/", "0000", "1111"), Message.INCORRECT_AMOUNT),
                Arguments.of(new TransferTransactForm("0", "0000", "1111"), Message.INCORRECT_AMOUNT),
                Arguments.of(new TransferTransactForm("-1", "0000", "1111"), Message.INCORRECT_AMOUNT),
                Arguments.of(new TransferTransactForm("-1000", "0000", "1111"), Message.INCORRECT_AMOUNT),
                Arguments.of(new TransferTransactForm("123", "0000", "0000"), Message.TRANSFER_ACCOUNTS_ARE_SAME)
        );
    }
}