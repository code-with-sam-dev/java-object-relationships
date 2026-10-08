package com.example.payments.polymorphism.overriding;

public record BankTransfer(String sortCode)
        implements PaymentMethod {

    @Override
    public long fee(long pence) {
        return 30;
    }
}
