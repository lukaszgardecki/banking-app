package com.example.app.transact;

import com.example.app.account.AccountRepository;
import com.example.app.exceptions.form.EmptyFieldException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactServiceTest {
    private final AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
    private final TransactService transactService = new TransactService(accountRepository);

    private static Stream<Arguments> validateValues() {
        return Stream.of(
                Arguments.of("", "", TransactService.EMPTY_FIELD_MESSAGE),
                Arguments.of("", "0000", TransactService.AMOUNT_EMPTY_MESSAGE),
                Arguments.of("123", "", TransactService.ACCOUNT_DEPOSITING_TO_EMPTY_MESSAGE),
                Arguments.of("badData", "0000", TransactService.BAD_DATA_INPUT_MESSAGE),
                Arguments.of("/", "0000", TransactService.BAD_DATA_INPUT_MESSAGE),
                Arguments.of("0", "0000", TransactService.BAD_DATA_INPUT_MESSAGE),
                Arguments.of("-1", "0000", TransactService.BAD_DATA_INPUT_MESSAGE),
                Arguments.of("-1000", "0000", TransactService.BAD_DATA_INPUT_MESSAGE)
        );
    }

    @ParameterizedTest
    @MethodSource
    void validateValues(String depositAmountStr, String accountId, String expected) {
        String message = "";
        try {
            transactService.validateValues(depositAmountStr, accountId);
        } catch (EmptyFieldException | NumberFormatException e) {
            message = e.getMessage();
        }
        assertEquals(expected, message);
    }
}