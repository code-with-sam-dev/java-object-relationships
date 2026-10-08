package com.example.payments.polymorphism.switching;

public record Card(String last4) implements PaymentMethod {}
