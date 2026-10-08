package com.example.payments.inheritance;

import java.util.List;

// Inheritance: a Ledger that also counts what it records.
public class CountingLedger extends Ledger {

    private int recorded;

    @Override
    public void record(Payment payment) {
        recorded++;
        super.record(payment);
    }

    @Override
    public void recordAll(List<Payment> payments) {
        recorded += payments.size();
        super.recordAll(payments);
    }

    public int recorded() {
        return recorded;
    }
}
