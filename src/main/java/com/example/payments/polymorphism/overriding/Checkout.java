package com.example.payments.polymorphism.overriding;

// Never asks which kind of payment it has.
public class Checkout {

    public long total(PaymentMethod method, long pence) {
        return pence + method.fee(pence);
    }
}
