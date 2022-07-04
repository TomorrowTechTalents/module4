package cryptosimulator;

import java.math.BigDecimal;

class Currency {
    private final String name;
    private final BigDecimal price;

    Currency(String pName, BigDecimal pPrice) {
        this.name = pName;
        this.price = pPrice;
    }

    String getName() {
        return this.name;
    }

    BigDecimal getPrice() {
        return this.price;
    }
}
