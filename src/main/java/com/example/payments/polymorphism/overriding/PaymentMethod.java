package com.example.payments.polymorphism.overriding;

// Every kind of payment says what it costs.
public interface PaymentMethod {

    long fee(long cents);
}
