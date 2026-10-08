package com.example.payments.polymorphism.overriding;

// Every kind of payment says what it costs.
public sealed interface PaymentMethod
        permits Card, BankTransfer, MobileMoney {

    long fee(long pence);
}
