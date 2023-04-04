package com.example.app.transact;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class TransactForm {
    private String amount;
    private String account;
}
