package com.example.payments.polymorphism.overriding;

import java.util.List;

public class Demo {

    void main() {
        var checkout = new Checkout();
        List<PaymentMethod> methods = List.of(
                new Card("4242"),
                new BankTransfer("20-00-00"),
                new MobileMoney("07700 900123"));

        for (var method : methods) {
            IO.println(method.getClass().getSimpleName()
                    + " total on 2500p: "
                    + checkout.total(method, 2_500) + "p");
        }
    }
}
