package com.example.payments.inheritance;

import java.util.List;

// Delegation: holds a PaymentLog and forwards to it,
// counting a call only once the log has accepted it.
public final class CountingLog implements PaymentLog {

    private final PaymentLog log;
    private int recorded;

    public CountingLog(PaymentLog log) {
        this.log = log;
    }

    @Override
    public void record(Payment payment) {
        log.record(payment);
        recorded++;
    }

    @Override
    public void recordAll(List<Payment> payments) {
        log.recordAll(payments);
        recorded += payments.size();
    }

    @Override
    public int size() {
        return log.size();
    }

    public int recorded() {
        return recorded;
    }
}
