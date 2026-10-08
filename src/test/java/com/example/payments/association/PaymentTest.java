package com.example.payments.association;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PaymentTest {

    @Test
    void twoPaymentsShareOneCustomer() {
        var sarah = new Customer("C-1", "Sarah Thompson");

        var first = new Payment("P-1", sarah, 2_500);
        var second = new Payment("P-2", sarah, 4_000);

        assertThat(first.customer()).isSameAs(sarah);
        assertThat(second.customer()).isSameAs(sarah);
    }
}
