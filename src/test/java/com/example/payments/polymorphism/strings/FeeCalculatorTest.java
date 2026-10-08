package com.example.payments.polymorphism.strings;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FeeCalculatorTest {

    FeeCalculator fees = new FeeCalculator();

    @Test
    void cardIsTwentyCentsPlusOneAndAHalfPercent() {
        assertThat(fees.fee("CARD", 2_500)).isEqualTo(57);
    }

    @Test
    void bankTransferIsAFlatThirtyCents() {
        assertThat(fees.fee("BANK_TRANSFER", 2_500))
                .isEqualTo(30);
    }

    @Test
    void anUnknownMethodQuietlyCostsNothing() {
        assertThat(fees.fee("MOBILE_MONEY", 2_500)).isZero();
    }

    @Test
    void aTypoCompilesAndCostsNothing() {
        assertThat(fees.fee("MOBILE_MOENY", 2_500)).isZero();
    }
}
