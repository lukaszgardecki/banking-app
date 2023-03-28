package com.example.app.exceptions.form;

public class MissingFormFieldsException extends RuntimeException{
    public MissingFormFieldsException() {
        super("Complete the missing fields");
    }
}
