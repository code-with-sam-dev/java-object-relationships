package com.example.payments.polymorphism.switching;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FeesTest {

    @Test
    void theSwitchGivesTheSameFeesAsTheOverrides() {
        assertThat(Fees.fee(new Card("4242"), 2_500))
                .isEqualTo(57);
        assertThat(Fees.fee(new BankTransfer("20-00-00"),
                2_500)).isEqualTo(30);
        assertThat(Fees.fee(new MobileMoney("07700 900123"),
                2_500)).isEqualTo(50);
    }
}
