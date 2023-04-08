package com.example.app.helpers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountNumGeneratorTest {

    @ParameterizedTest
    @CsvSource({
            "1, 90127720550000000000000001",
            "2, 93127720550000000000000002",
            "3, 96127720550000000000000003",
            "10, 90127720550000000000000010",
            "113, 27127720550000000000000113",
            "456890, 36127720550000000000456890",
            "54, 18127720550000000000000054"
    })
    void shouldGenerateCorrectAccountNumber(BigDecimal userId, String expectedNumber) {
        String accountNum = AccountNumGenerator.generate(userId);
        assertEquals(accountNum, expectedNumber);
    }
}