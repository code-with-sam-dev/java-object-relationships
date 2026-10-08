package com.example.payments.composition;

import java.util.ArrayList;

public class Demo {

    void main() {
        var sarah = new Customer("C-1", "Sarah Thompson");

        var leakyHistory = new ArrayList<StatusChange>();
        var leaky = new LeakyPayment("P-1", sarah, 2_500,
                leakyHistory);
        leaky.authorise();

        var ownedHistory = new ArrayList<StatusChange>();
        var owned = new Payment("P-2", sarah, 2_500,
                ownedHistory);
        owned.authorise();

        // Nobody calls capture(). The caller edits its list.
        var fake = new StatusChange(Status.AUTHORISED,
                Status.CAPTURED);
        leakyHistory.add(fake);
        ownedHistory.add(fake);

        IO.println("LeakyPayment status: " + leaky.status());
        IO.println("Payment status:      " + owned.status());
    }
}
