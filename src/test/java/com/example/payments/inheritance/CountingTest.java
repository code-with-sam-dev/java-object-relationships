package com.example.payments.inheritance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void theWrapperDoesNotCountAPaymentTheLogRefused() {
        var log = new CountingLog(new RefusingLog());

        assertThatThrownBy(() -> log.recordAll(payments))
                .isInstanceOf(IllegalStateException.class);
        assertThat(log.recorded()).isZero();
    }

    /** A log that is down: every write fails. */
    static class RefusingLog extends Ledger {

        @Override
        public void record(Payment payment) {
            throw new IllegalStateException("ledger is down");
        }

        @Override
        public void recordAll(List<Payment> payments) {
            throw new IllegalStateException("ledger is down");
        }
    }
}
