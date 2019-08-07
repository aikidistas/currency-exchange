package org.aikidistas.currencyexchange.domain.rate;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RateTest {

    @Test
    public void testToString() {
        assertEquals("Rate(amount=1.234)", new Rate.Fake(1.234).toString());
    }
}