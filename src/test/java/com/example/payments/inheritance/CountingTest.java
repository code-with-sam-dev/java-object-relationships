package com.example.payments.inheritance;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class CountingTest {

    List<Payment> payments = List.of(
            new Payment("P-1", 2_500),
            new Payment("P-2", 4_000),
            new Payment("P-3", 1_200));

    @Test
    void theSubclassCountsEveryPaymentTwice() {
        var ledger = new CountingLedger();
        ledger.recordAll(payments);

        assertThat(ledger.size()).isEqualTo(3);
        assertThat(ledger.recorded()).isEqualTo(6);
    }

    @Test
    void theWrapperCountsEachPaymentOnce() {
        var log = new CountingLog(new Ledger());
        log.recordAll(payments);

        assertThat(log.size()).isEqualTo(3);
        assertThat(log.recorded()).isEqualTo(3);
    }
}
