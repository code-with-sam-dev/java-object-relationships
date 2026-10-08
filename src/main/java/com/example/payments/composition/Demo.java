package com.example.payments.composition;

public class Demo {

    void main() {
        var sarah = new Customer("C-1", "Sarah Thompson");

        var shared = new PaymentHistory();
        var p1 = new SharedHistoryPayment("P-1", sarah, 2_500,
                shared);
        var p2 = new SharedHistoryPayment("P-2", sarah, 4_000,
                shared);
        p1.authorise();
        IO.println("SharedHistoryPayment P-1: " + p1.status());
        IO.println("SharedHistoryPayment P-2: " + p2.status());

        var p3 = new Payment("P-3", sarah, 2_500);
        var p4 = new Payment("P-4", sarah, 4_000);
        p3.authorise();
        IO.println("Payment P-3: " + p3.status());
        IO.println("Payment P-4: " + p4.status());
    }
}
