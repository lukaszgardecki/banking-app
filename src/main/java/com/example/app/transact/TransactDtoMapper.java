package com.example.app.transact;

public class TransactDtoMapper {

    public static Transact map(TransactDto dto) {
        Transact transact = new Transact();
        transact.setAccount_id(dto.getAccountId());
        transact.setTransaction_type(dto.getTransactionType());
        transact.setAmount(dto.getAmount());
        transact.setSource(dto.getSource());
        transact.setStatus(dto.getStatus());
        transact.setReason_code(dto.getReasonCode());
        transact.setCreated_at(dto.getCreatedAt());
        return transact;
    }
}
