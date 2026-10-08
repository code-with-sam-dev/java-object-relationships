package com.example.payments.polymorphism.overriding;

public record MobileMoney(String phone)
        implements PaymentMethod {}
