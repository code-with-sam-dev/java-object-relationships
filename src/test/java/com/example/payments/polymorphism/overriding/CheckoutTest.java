package com.example.payments.polymorphism.overriding;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CheckoutTest {

    Checkout checkout = new Checkout();

    @Test
    void eachMethodAddsItsOwnFee() {
        assertThat(checkout.total(new Card("4242"), 2_500))
                .isEqualTo(2_557);
        assertThat(checkout.total(
                new BankTransfer("20-00-00"), 2_500))
                .isEqualTo(2_530);
        assertThat(checkout.total(
                new MobileMoney("07700 900123"), 2_500))
                .isEqualTo(2_550);
    }

    @Test
    void checkoutNeverAsksWhichMethodItHas() {
        PaymentMethod method = new Card("4242");
        long card = checkout.total(method, 2_500);
        method = new MobileMoney("07700 900123");
        long mobile = checkout.total(method, 2_500);

        assertThat(card).isEqualTo(2_557);
        assertThat(mobile).isEqualTo(2_550);
    }
}
