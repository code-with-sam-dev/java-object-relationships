package com.example.payments.composition;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class PaymentTest {

    Customer sarah = new Customer("C-1", "Sarah Thompson");

    @Test
    void movesThroughItsStatuses() {
        var payment = new Payment("P-1", sarah, 2_500);
        payment.authorise();
        payment.capture();

        assertThat(payment.status()).isEqualTo(Status.CAPTURED);
        assertThat(payment.history()).containsExactly(
                new StatusChange(Status.CREATED,
                        Status.AUTHORISED),
                new StatusChange(Status.AUTHORISED,
                        Status.CAPTURED));
    }

    @Test
    void eachPaymentHasItsOwnHistory() {
        var first = new Payment("P-1", sarah, 2_500);
        var second = new Payment("P-2", sarah, 4_000);
        first.authorise();

        assertThat(second.status()).isEqualTo(Status.CREATED);
    }

    @Test
    void theHistoryItHandsOutCannotBeChanged() {
        var payment = new Payment("P-1", sarah, 2_500);
        var fake = new StatusChange(Status.AUTHORISED,
                Status.CAPTURED);

        assertThatThrownBy(() -> payment.history().add(fake))
                .isInstanceOf(
                        UnsupportedOperationException.class);
        assertThat(payment.status()).isEqualTo(Status.CREATED);
    }

    @Test
    void cannotCaptureBeforeItIsAuthorised() {
        var payment = new Payment("P-1", sarah, 2_500);

        assertThatThrownBy(payment::capture)
                .isInstanceOf(IllegalStateException.class)
                .isInstanceOf(PaymentStateException.class)
                .hasMessage("P-1 is CREATED, not AUTHORISED");
    }

    @Test
    void aSharedHistoryLetsOnePaymentChangeAnother() {
        var shared = new PaymentHistory();
        var first = new SharedHistoryPayment("P-1", sarah,
                2_500, shared);
        var second = new SharedHistoryPayment("P-2", sarah,
                4_000, shared);
        first.authorise();

        assertThat(second.status())
                .isEqualTo(Status.AUTHORISED);
    }
}
