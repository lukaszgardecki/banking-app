package com.example.app.helpers;

import java.math.BigDecimal;

public class AccountNumGenerator {
    public static final int BANK_NUM = 12772055;
    public static final int USER_NUM_LEN = 16;
    public static final int CONTROL_SUM_LEN = 2;

    public static String generate(BigDecimal newId) {
        String userNumber = createUserNum(newId);
        String controlSum = getControlSum(userNumber);
        return controlSum + BANK_NUM + userNumber;
    }

    private static String getControlSum(String userNum) {
        String preAccNum = BANK_NUM + userNum;
        while (preAccNum.length() != CONTROL_SUM_LEN) {
            preAccNum = doSumLoop(preAccNum);
        }
        return preAccNum;
    }

    private static String doSumLoop(String num) {
        int increasingParam = 3;
        int sum = 0;

        for (int i = 0; i < num.length(); i++) {
            String digitStr = Character.toString(num.charAt(i));
            int digitInt = Integer.parseInt(digitStr);
            sum += (digitInt * increasingParam);
        }
        return String.valueOf(sum);
    }

    private static String createUserNum(BigDecimal maxAccountId) {
        int amountOfZeros = USER_NUM_LEN - maxAccountId.toString().length();
        String leadZeros = "0".repeat(amountOfZeros);
        return leadZeros + maxAccountId;
    }
}
