package com.example.payments.polymorphism.overriding;

public record MobileMoney(String phone)
        implements PaymentMethod {

    @Override
    public long fee(long pence) {
        return pence * 2 / 100;
    }
}
