package com.example.payments.association;

// A payment knows its customer. The customer was created
// first, by someone else, and does not know its payments.
public record Payment(String id, Customer customer,
        long pence) {}
