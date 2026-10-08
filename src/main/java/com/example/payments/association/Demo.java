package com.example.payments.association;

public class Demo {

    void main() {
        var sarah = new Customer("C-1", "Sarah Thompson");

        var first = new Payment("P-1", sarah, 2_500);
        var second = new Payment("P-2", sarah, 4_000);

        IO.println("P-1 customer: " + first.customer().name());
        IO.println("P-2 customer: " + second.customer().name());
        IO.println("same Customer object: "
                + (first.customer() == second.customer()));
    }
}
