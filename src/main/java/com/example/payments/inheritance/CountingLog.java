package com.example.payments.inheritance;

import java.util.List;

// Composition: holds a PaymentLog and forwards to it.
public final class CountingLog implements PaymentLog {

    private final PaymentLog log;
    private int recorded;

    public CountingLog(PaymentLog log) {
        this.log = log;
    }

    @Override
    public void record(Payment payment) {
        recorded++;
        log.record(payment);
    }

    @Override
    public void recordAll(List<Payment> payments) {
        recorded += payments.size();
        log.recordAll(payments);
    }

    @Override
    public int size() {
        return log.size();
    }

    public int recorded() {
        return recorded;
    }
}
