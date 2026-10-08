package com.example.payments.polymorphism.switching;

public record MobileMoney(String phone)
        implements PaymentMethod {}
