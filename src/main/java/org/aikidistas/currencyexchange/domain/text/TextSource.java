package org.aikidistas.currencyexchange.domain.text;

import org.aikidistas.currencyexchange.domain.exception.DomainException;

public interface TextSource {
    String text() throws DomainException;
}
