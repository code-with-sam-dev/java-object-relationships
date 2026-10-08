package com.example.payments.polymorphism.strings;

// One method decides the fee for every kind of payment.
public class FeeCalculator {

    public long fee(String method, long cents) {
        if (method.equals("CARD")) {
            return 20 + cents * 15 / 1000;
        } else if (method.equals("BANK_TRANSFER")) {
            return 30;
        }
        return 0;
    }
}
