package com.example.payments.polymorphism.overriding;

public record MobileMoney(String phone)
        implements PaymentMethod {

    @Override
    public long fee(long cents) {
        return cents * 2 / 100;
    }
}
