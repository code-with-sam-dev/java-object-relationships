package com.example.payments.polymorphism.overriding;

public record Card(String last4) implements PaymentMethod {

    @Override
    public long fee(long cents) {
        return 20 + cents * 15 / 1000;
    }
}
