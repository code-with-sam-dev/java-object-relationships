package com.example.payments.inheritance;

import java.util.List;

public interface PaymentLog {

    void record(Payment payment);

    void recordAll(List<Payment> payments);

    int size();
}
