package cryptosimulator;

import java.time.LocalDate;

class Order {
    private final Long amount;
    private final Currency currency;
    private final LocalDate date;

    Order(Long pAmount, Currency pCurrency, LocalDate pDate) {
        this.amount = pAmount;
        this.currency = pCurrency;
        this.date = pDate;
    }

    Long getAmount() {
        return this.amount;
    }

    Currency getCurrency() {
        return this.currency;
    }

    LocalDate getDate() {
        return this.date;
    }
}
