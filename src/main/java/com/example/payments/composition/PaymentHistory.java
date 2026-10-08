package com.example.payments.composition;

import java.util.ArrayList;
import java.util.List;

// The status changes of one payment, in order.
public final class PaymentHistory {

    private final List<StatusChange> changes =
            new ArrayList<>();

    void add(StatusChange change) {
        changes.add(change);
    }

    public Status current() {
        return changes.isEmpty()
                ? Status.CREATED
                : changes.getLast().to();
    }

    public List<StatusChange> entries() {
        return List.copyOf(changes);
    }
}
