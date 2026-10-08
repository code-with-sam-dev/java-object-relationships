package com.example.payments.polymorphism.switching;

// Every fee rule in one place, checked by the compiler.
public final class Fees {

    private Fees() {}

    public static long fee(PaymentMethod method, long cents) {
        return switch (method) {
            case Card card -> 20 + cents * 15 / 1000;
            case BankTransfer transfer -> 30;
        };
    }
}
