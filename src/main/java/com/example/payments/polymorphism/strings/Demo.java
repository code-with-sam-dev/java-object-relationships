package com.example.payments.polymorphism.strings;

public class Demo {

    void main() {
        var fees = new FeeCalculator();
        for (var method : new String[] {
                "CARD", "BANK_TRANSFER", "MOBILE_MONEY",
                "MOBILE_MOENY" }) {
            IO.println(method + " fee on 2500 cents: "
                    + fees.fee(method, 2_500) + " cents");
        }
    }
}
