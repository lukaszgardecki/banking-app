package com.example.app.exceptions.transact;

public class TooLowBalanceException extends RuntimeException {
    public TooLowBalanceException(String message) {
        super(message);
    }
}
