package com.example.payments.composition;

// Inheritance that works: a payment state error IS an
// IllegalStateException, and every catch written for one
// still catches it.
public class PaymentStateException
        extends IllegalStateException {

    public PaymentStateException(String message) {
        super(message);
    }
}
