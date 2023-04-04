package com.example.app.transact.forms;

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
