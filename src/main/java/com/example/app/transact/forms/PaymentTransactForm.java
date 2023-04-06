package com.example.app.transact.forms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentTransactForm extends TransactForm {
    private String amount;
    private String accountFromId;
    private String beneficiary;
    private String beneficiaryAccNo;
    private String reference;

    public PaymentTransactForm(String amount, String accountFromId, String beneficiary, String beneficiaryAccNo, String reference) {
        this.amount = amount;
        this.accountFromId = accountFromId;
        this.beneficiary = beneficiary;
        this.beneficiaryAccNo = beneficiaryAccNo;
        this.reference = reference;
        super.setAmount(amount);
        super.setAccountFromId(accountFromId);
        super.setBeneficiary(beneficiary);
        super.setAccountToNumber(beneficiaryAccNo);
        super.setReference(reference);
    }

}
