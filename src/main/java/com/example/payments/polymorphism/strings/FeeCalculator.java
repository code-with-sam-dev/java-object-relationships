package com.example.payments.polymorphism.strings;

// One method decides the fee for every kind of payment.
public class FeeCalculator {

    public long fee(String method, long pence) {
        if (method.equals("CARD")) {
            return 20 + pence * 15 / 1000;
        } else if (method.equals("BANK_TRANSFER")) {
            return 30;
        }
        return 0;
    }
}
