package com.example.app.helpers;

public class Message {
    public static final String MISSING_FIELDS = "Complete the missing fields";
    public static final String CONFIRM_PASS_REQUIRED = "The confirm field is required";
    public static final String PASSWORDS_ARE_NOT_SAME = "Passwords must be the same";

    public static final String INCORRECT_AMOUNT = "Please enter the correct amount";
    public static final String TOO_LOW_BALANCE = "Too little funds in the account";

    public static final String DEPOSIT_AMOUNT_FIELD_EMPTY = "Deposit amount cannot be empty";
    public static final String DEPOSIT_ACCOUNT_TO_EMPTY = "Account depositing to cannot be empty";
    public static final String DEPOSIT_SUCCESS = "Account deposited successfully!";
    public static final String DEPOSIT_REASON_CODE_SUCCESS = "Deposit transaction successful";
    public static final String DEPOSIT_TRANSACTION_TYPE = "deposit";

    public static final String TRANSFER_AMOUNT_FIELD_EMPTY = "Transfer amount cannot be empty";
    public static final String TRANSFER_ACCOUNT_FROM_EMPTY = "The account transferring from cannot be empty";
    public static final String TRANSFER_ACCOUNT_TO_EMPTY = "The account transferring to cannot be empty";
    public static final String TRANSFER_ACCOUNTS_ARE_SAME = "Cannot transfer into the same account. Please select the appropriate account to perform transfer";
    public static final Object TRANSFER_SUCCESS = "Amount transferred successfully!";
    public static final String TRANSFER_REASON_CODE_SUCCESS = "Transfer transaction successful" ;
    public static final String TRANSFER_TRANSACTION_TYPE = "transfer";

    public static final String WITHDRAW_AMOUNT_FIELD_EMPTY = "Withdrawal amount cannot be empty";
    public static final String WITHDRAW_ACCOUNT_FROM_EMPTY = "Account withdrawing from cannot be empty";
    public static final String WITHDRAW_SUCCESS = "You have successfully withdraw %s from your bank account!";
    public static final String WITHDRAW_REASON_CODE_FAILURE = "Insufficient funds";
    public static final String WITHDRAW_REASON_CODE_SUCCESS = "Withdrawal transaction successful" ;
    public static final String WITHDRAW_TRANSACTION_TYPE = "withdraw";

    public static final String PAYMENT_AMOUNT_FIELD_EMPTY = "Payment amount cannot be empty";
    public static final String PAYMENT_BENEFICIARY_EMPTY = "Beneficiary cannot be empty";
    public static final String PAYMENT_ACCOUNT_NO_EMPTY = "Account number cannot be empty";
    public static final String PAYMENT_ACCOUNT_FROM_EMPTY = "Account paying from cannot be empty";
    public static final String PAYMENT_CODE_SUCCESS = "Payment processed successfully";
    public static final String PAYMENT_REASON_CODE_SUCCESS = "Payment transaction successful" ;
    public static final String PAYMENT_TRANSACTION_TYPE = "payment";

    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_FAILED = "failed";
    public static final String SOURCE = "online";
}
