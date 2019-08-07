package org.aikidistas.currencyexchange;

import org.junit.Test;

public class ApplicationRunnerTest {
    @Test
    public void mainExists() {
        ApplicationRunner.main("EUR/DKK", "1");
    }
}
