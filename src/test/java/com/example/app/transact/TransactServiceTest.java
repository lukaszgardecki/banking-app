package com.example.app.transact;

import com.example.app.exceptions.form.EmptyFieldException;
import com.example.app.helpers.Message;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactServiceTest {
    private final TransactService transactService = new TransactService();

    private static Stream<Arguments> validateValues() {
        return Stream.of(
                Arguments.of(new DepositTransactForm("", ""), Message.MISSING_FIELDS),
                Arguments.of(new DepositTransactForm("", "0000"), Message.DEPOSIT_AMOUNT_FIELD_EMPTY),
                Arguments.of(new DepositTransactForm("123", ""), Message.DEPOSIT_ACCOUNT_TO_EMPTY),
                Arguments.of(new DepositTransactForm("badData", "0000"), Message.INCORRECT_AMOUNT),
                Arguments.of(new DepositTransactForm("/", "0000"), Message.INCORRECT_AMOUNT),
                Arguments.of(new DepositTransactForm("0", "0000"), Message.INCORRECT_AMOUNT),
                Arguments.of(new DepositTransactForm("-1", "0000"), Message.INCORRECT_AMOUNT),
                Arguments.of(new DepositTransactForm("-1000", "0000"), Message.INCORRECT_AMOUNT)
        );
    }

    @ParameterizedTest
    @MethodSource
    void validateValues(TransactForm transactForm, String expected) {
        String message = "";
        try {
            transactService.validateForm(transactForm);
        } catch (EmptyFieldException | NumberFormatException e) {
            message = e.getMessage();
        }
        assertEquals(expected, message);
    }
}