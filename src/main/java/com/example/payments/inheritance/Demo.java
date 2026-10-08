package com.example.payments.inheritance;

import java.util.List;

public class Demo {

    void main() {
        var payments = List.of(
                new Payment("P-1", 2_500),
                new Payment("P-2", 4_000),
                new Payment("P-3", 1_200));

        var inherited = new CountingLedger();
        inherited.recordAll(payments);
        IO.println("CountingLedger: size " + inherited.size()
                + ", recorded " + inherited.recorded());

        var composed = new CountingLog(new Ledger());
        composed.recordAll(payments);
        IO.println("CountingLog:    size " + composed.size()
                + ", recorded " + composed.recorded());
    }
}
