package com.example.app.exceptions.form;

public class SameAccountsFieldsException extends RuntimeException {
    public SameAccountsFieldsException(String message) {
        super(message);
    }
}
