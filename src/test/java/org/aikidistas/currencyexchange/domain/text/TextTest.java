package org.aikidistas.currencyexchange.domain.text;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TextTest {

    @Test
    public void text() {
        String expectedText = "TEXT";
        Text textSource = new Text(expectedText);
        assertEquals(expectedText, textSource.text());
    }
}