package org.aikidistas.currencyexchange.domain.text;

import com.jcabi.aspects.Immutable;

@Immutable
public class Text implements TextSource {
    private final String stringValue;

    public Text(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String text() {
        return stringValue;
    }
}
