package com.example.payments.aggregation;

public record Payment(String id, Customer customer,
        long cents) {}
