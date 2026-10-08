package com.example.payments.aggregation;

import java.util.ArrayList;
import java.util.List;

// Groups payments to send to the bank at the end of the day.
// The payments existed before the batch and outlive it.
public final class SettlementBatch {

    private final String id;
    private final List<Payment> payments = new ArrayList<>();
    private boolean rejected;

    public SettlementBatch(String id) {
        this.id = id;
    }

    public void add(Payment payment) {
        payments.add(payment);
    }

    public void reject() {
        rejected = true;
    }

    public boolean rejected() {
        return rejected;
    }

    public long totalPence() {
        return payments.stream()
                .mapToLong(Payment::pence)
                .sum();
    }

    public List<Payment> payments() {
        return List.copyOf(payments);
    }

    public String id() {
        return id;
    }
}
