package com.example.payments.polymorphism.switching;

// Every fee rule in one place, checked by the compiler.
public final class Fees {

    private Fees() {}

    public static long fee(PaymentMethod method, long pence) {
        return switch (method) {
            case Card card -> 20 + pence * 15 / 1000;
            case BankTransfer transfer -> 30;
        };
    }
}
