package com.example.payments.polymorphism.overriding;

public record Card(String last4) implements PaymentMethod {

    @Override
    public long fee(long pence) {
        return 20 + pence * 15 / 1000;
    }
}
