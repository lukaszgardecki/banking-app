package com.example.app.transact;

import com.example.app.exceptions.form.EmptyFieldException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactServiceTest {
    private final TransactService transactService = new TransactService();

    private static Stream<Arguments> validateValues() {
        return Stream.of(
                Arguments.of(new DepositTransactForm("", ""), TransactService.EMPTY_FIELD_MESSAGE),
                Arguments.of(new DepositTransactForm("", "0000"), TransactService.AMOUNT_EMPTY_MESSAGE),
                Arguments.of(new DepositTransactForm("123", ""), TransactService.ACCOUNT_DEPOSITING_TO_EMPTY_MESSAGE),
                Arguments.of(new DepositTransactForm("badData", "0000"), TransactService.BAD_DATA_INPUT_MESSAGE),
                Arguments.of(new DepositTransactForm("/", "0000"), TransactService.BAD_DATA_INPUT_MESSAGE),
                Arguments.of(new DepositTransactForm("0", "0000"), TransactService.BAD_DATA_INPUT_MESSAGE),
                Arguments.of(new DepositTransactForm("-1", "0000"), TransactService.BAD_DATA_INPUT_MESSAGE),
                Arguments.of(new DepositTransactForm("-1000", "0000"), TransactService.BAD_DATA_INPUT_MESSAGE)
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