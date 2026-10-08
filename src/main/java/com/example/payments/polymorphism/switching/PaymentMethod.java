package com.example.payments.polymorphism.switching;

// The kinds of payment are data. The fee lives elsewhere.
public sealed interface PaymentMethod
        permits Card, BankTransfer, MobileMoney {}
