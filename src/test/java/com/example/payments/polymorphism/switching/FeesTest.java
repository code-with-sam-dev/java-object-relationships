package com.example.payments.polymorphism.switching;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FeesTest {

    @Test
    void theSwitchGivesTheSameFeesAsTheOverrides() {
        assertThat(Fees.fee(new Card("4242"), 2_500))
                .isEqualTo(57);
        assertThat(Fees.fee(new BankTransfer("123456789"),
                2_500)).isEqualTo(30);
        assertThat(Fees.fee(new MobileMoney("+1 555 0100"),
                2_500)).isEqualTo(50);
    }
}
