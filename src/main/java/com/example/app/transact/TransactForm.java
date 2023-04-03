package com.example.app.transact;

import lombok.Getter;

@Getter
public abstract class TransactForm {
    private String amount;

    public TransactForm(String amount) {
        this.amount = amount;
    }
}
