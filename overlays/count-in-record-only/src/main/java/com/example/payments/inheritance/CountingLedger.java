package com.example.payments.inheritance;

// The obvious fix: only count in record(), because
// recordAll() already calls record() for every payment.
public class CountingLedger extends Ledger {

    private int recorded;

    @Override
    public void record(Payment payment) {
        recorded++;
        super.record(payment);
    }

    public int recorded() {
        return recorded;
    }
}
