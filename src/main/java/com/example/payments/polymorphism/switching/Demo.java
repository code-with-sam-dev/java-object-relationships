package com.example.payments.polymorphism.switching;

import java.util.List;

public class Demo {

    void main() {
        List<PaymentMethod> methods = List.of(
                new Card("4242"),
                new BankTransfer("20-00-00"),
                new MobileMoney("07700 900123"));

        for (var method : methods) {
            IO.println(method.getClass().getSimpleName()
                    + " fee on 2500p: "
                    + Fees.fee(method, 2_500) + "p");
        }
    }
}
