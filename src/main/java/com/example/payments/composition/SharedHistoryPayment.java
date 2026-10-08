package com.example.payments.composition;

import java.util.List;

// Takes its history from the caller, so two payments can
// be given the same one.
public final class SharedHistoryPayment {

    private final String id;
    private final Customer customer;
    private final long cents;
    private final PaymentHistory history;

    public SharedHistoryPayment(String id, Customer customer,
            long cents,
            PaymentHistory history) {
        this.id = id;
        this.customer = customer;
        this.cents = cents;
        this.history = history;
    }

    public void authorise() {
        move(Status.CREATED, Status.AUTHORISED);
    }

    public void capture() {
        move(Status.AUTHORISED, Status.CAPTURED);
    }

    public Status status() {
        return history.current();
    }

    public List<StatusChange> history() {
        return history.entries();
    }

    private void move(Status from, Status to) {
        if (status() != from) {
            throw new PaymentStateException(
                    id + " is " + status() + ", not " + from);
        }
        history.add(new StatusChange(from, to));
    }
}
