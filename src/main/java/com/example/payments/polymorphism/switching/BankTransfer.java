package com.example.payments.polymorphism.switching;

public record BankTransfer(String routingNumber)
        implements PaymentMethod {}
