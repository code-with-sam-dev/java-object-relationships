package com.example.payments.composition;

import java.util.List;

// Owns its history: it creates it, changes it only through
// authorise() and capture(), and never hands it out.
public final class Payment {

    private final String id;
    private final Customer customer;
    private final long cents;
    private final PaymentHistory history =
            new PaymentHistory();

    public Payment(String id, Customer customer,
            long cents) {
        this.id = id;
        this.customer = customer;
        this.cents = cents;
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
