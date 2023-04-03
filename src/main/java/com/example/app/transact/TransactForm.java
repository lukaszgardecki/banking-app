package com.example.app.transact;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class TransactForm {
    private String amount;

    public TransactForm(String amount) {
        this.amount = amount;
    }
}
