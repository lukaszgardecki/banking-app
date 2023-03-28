package com.example.app.exceptions.form;

public class ConfirmPassFieldIsEmpty extends RuntimeException{
    public ConfirmPassFieldIsEmpty() {
        super("The confirm field is required");
    }
}
