package com.example.app.exceptions.form;

public class PasswordsAreNotTheSameException extends RuntimeException{
    public PasswordsAreNotTheSameException() {
        super("Passwords must be the same");
    }
}
