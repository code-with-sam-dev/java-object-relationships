package com.example.payments.aggregation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SettlementBatchTest {

    Customer sarah = new Customer("C-1", "Sarah Thompson");
    Payment first = new Payment("P-1", sarah, 2_500);
    Payment second = new Payment("P-2", sarah, 4_000);

    @Test
    void totalsThePaymentsItWasGiven() {
        var batch = new SettlementBatch("MON");
        batch.add(first);
        batch.add(second);

        assertThat(batch.totalPence()).isEqualTo(6_500);
    }

    @Test
    void theSamePaymentsCanMoveToAnotherBatch() {
        var monday = new SettlementBatch("MON");
        monday.add(first);
        var tuesday = new SettlementBatch("TUE");
        tuesday.add(first);

        assertThat(tuesday.payments().getFirst())
                .isSameAs(monday.payments().getFirst());
    }
}
