package com.example.app.transact;

import lombok.Getter;

@Getter
public class PaymentTransactForm extends TransactForm {
    private String beneficiary;
    private String accountNumber;
    private String reference;

    public PaymentTransactForm(String amount, String beneficiary, String accountNumber, String accountFrom, String reference) {
        super(amount, accountFrom);
        this.beneficiary = beneficiary;
        this.accountNumber = accountNumber;
        this.reference = reference;
    }
}
