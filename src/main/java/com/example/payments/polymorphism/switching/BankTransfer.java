package com.example.payments.polymorphism.switching;

public record BankTransfer(String sortCode)
        implements PaymentMethod {}
