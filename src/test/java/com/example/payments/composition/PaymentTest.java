package com.example.payments.composition;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class PaymentTest {

    Customer sarah = new Customer("C-1", "Sarah Thompson");
    StatusChange fake = new StatusChange(Status.AUTHORISED,
            Status.CAPTURED);

    @Test
    void movesThroughItsStatuses() {
        var payment = new Payment("P-1", sarah, 2_500,
                List.of());
        payment.authorise();
        payment.capture();

        assertThat(payment.status()).isEqualTo(Status.CAPTURED);
        assertThat(payment.history()).hasSize(2);
    }

    @Test
    void cannotCaptureBeforeItIsAuthorised() {
        var payment = new Payment("P-1", sarah, 2_500,
                List.of());

        assertThatThrownBy(payment::capture)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("P-1 is CREATED, not AUTHORISED");
    }

    @Test
    void theCallersListCannotChangeAPayment() {
        var history = new ArrayList<StatusChange>();
        var payment = new Payment("P-1", sarah, 2_500, history);
        payment.authorise();

        history.add(fake);

        assertThat(payment.status())
                .isEqualTo(Status.AUTHORISED);
    }

    @Test
    void theHistoryItHandsOutIsACopy() {
        var payment = new Payment("P-1", sarah, 2_500,
                List.of());
        payment.authorise();

        assertThatThrownBy(() -> payment.history().add(fake))
                .isInstanceOf(
                        UnsupportedOperationException.class);
    }

    @Test
    void theLeakyVersionCanBeChangedFromOutside() {
        var history = new ArrayList<StatusChange>();
        var payment = new LeakyPayment("P-1", sarah, 2_500,
                history);
        payment.authorise();

        history.add(fake);

        assertThat(payment.status())
                .isEqualTo(Status.CAPTURED);
    }
}
