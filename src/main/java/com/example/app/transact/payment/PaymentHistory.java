package com.example.app.transact.payment;

import jakarta.persistence.*;

//@Entity
@Table(name = "v_payments")
public class PaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
