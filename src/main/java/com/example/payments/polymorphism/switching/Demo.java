package com.example.payments.polymorphism.switching;

import java.util.List;

public class Demo {

    void main() {
        List<PaymentMethod> methods = List.of(
                new Card("4242"),
                new BankTransfer("123456789"),
                new MobileMoney("+1 555 0100"));

        for (var method : methods) {
            IO.println(method.getClass().getSimpleName()
                    + " fee on 2500 cents: "
                    + Fees.fee(method, 2_500) + " cents");
        }
    }
}
