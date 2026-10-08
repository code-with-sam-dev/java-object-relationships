package com.example.payments.inheritance;

import java.util.ArrayList;
import java.util.List;

public class Ledger implements PaymentLog {

    private final List<Payment> entries = new ArrayList<>();

    @Override
    public void record(Payment payment) {
        entries.add(payment);
    }

    @Override
    public void recordAll(List<Payment> payments) {
        entries.addAll(payments);
    }

    @Override
    public int size() {
        return entries.size();
    }
}
