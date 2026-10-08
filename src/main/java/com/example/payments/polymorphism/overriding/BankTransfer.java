package com.example.payments.polymorphism.overriding;

public record BankTransfer(String routingNumber)
        implements PaymentMethod {

    @Override
    public long fee(long cents) {
        return 30;
    }
}
