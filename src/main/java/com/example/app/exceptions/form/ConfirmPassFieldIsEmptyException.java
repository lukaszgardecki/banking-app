package com.example.app.exceptions.form;

public class ConfirmPassFieldIsEmptyException extends RuntimeException{
    public ConfirmPassFieldIsEmptyException() {
        super("The confirm field is required");
    }
}
