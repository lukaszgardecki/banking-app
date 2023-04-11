package com.example.app.transact;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransactDto {
    private Long transactId;
    private Long accountId;
    private String transactionType;
    private String amount;
    private String source;
    private String status;
    private String reasonCode;
    private LocalDateTime createdAt;
}
