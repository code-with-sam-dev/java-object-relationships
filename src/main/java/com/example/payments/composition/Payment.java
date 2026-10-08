package com.example.payments.composition;

import java.util.ArrayList;
import java.util.List;

// Owns its history: it copies what it is given into its own
// list, and only it can change it. Nobody else holds that list.
public final class Payment {

    private final String id;
    private final Customer customer;
    private final long pence;
    private final List<StatusChange> history;

    public Payment(String id, Customer customer,
            long pence, List<StatusChange> history) {
        this.id = id;
        this.customer = customer;
        this.pence = pence;
        this.history = new ArrayList<>(history);
    }

    public void authorise() {
        move(Status.CREATED, Status.AUTHORISED);
    }

    public void capture() {
        move(Status.AUTHORISED, Status.CAPTURED);
    }

    public Status status() {
        return history.isEmpty()
                ? Status.CREATED
                : history.getLast().to();
    }

    public List<StatusChange> history() {
        return List.copyOf(history);
    }

    private void move(Status from, Status to) {
        if (status() != from) {
            throw new IllegalStateException(
                    id + " is " + status() + ", not " + from);
        }
        history.add(new StatusChange(from, to));
    }
}
