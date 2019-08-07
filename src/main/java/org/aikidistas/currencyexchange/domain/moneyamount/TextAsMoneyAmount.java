package org.aikidistas.currencyexchange.domain.moneyamount;

import com.jcabi.aspects.Immutable;
import org.aikidistas.currencyexchange.domain.exception.DomainException;
import org.aikidistas.currencyexchange.domain.text.Text;
import org.aikidistas.currencyexchange.domain.text.TextSource;

@Immutable
public class TextAsMoneyAmount implements MoneyAmountSource {
    private final TextSource textSource;

    public TextAsMoneyAmount(String textSource) {
        this(new Text(textSource));
    }

    public TextAsMoneyAmount(TextSource textSource) {
        this.textSource = textSource;
    }

    @Override
    public MoneyAmount moneyAmount() throws DomainException {
        return new MoneyAmount.Dto(textSource.text());
    }
}
