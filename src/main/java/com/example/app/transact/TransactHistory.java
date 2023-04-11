package com.example.app.transact;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "v_transaction_history")
@Getter
@Setter
public class TransactHistory {
    @Id
    private Long transactionId;
    private Long userId;
    private String first_name;
    private String last_name;
    private String street;
    private String city;
    private String zip_code;
    private String account_number;
    private String account_name;
    private String beneficiary;
    private String beneficiaryAccNo;
    private String transactionType;
    private BigDecimal amount;
//    private String currency;
    private String source;
    private String status;
    private String reasonCode;
    private LocalDateTime createdAt;
}
