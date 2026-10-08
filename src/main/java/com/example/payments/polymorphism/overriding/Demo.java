package com.example.payments.polymorphism.overriding;

import java.util.List;

public class Demo {

    void main() {
        var checkout = new Checkout();
        List<PaymentMethod> methods = List.of(
                new Card("4242"),
                new BankTransfer("123456789"),
                new MobileMoney("+1 555 0100"));

        for (var method : methods) {
            IO.println(method.getClass().getSimpleName()
                    + " total on 2500 cents: "
                    + checkout.total(method, 2_500) + " cents");
        }
    }
}
