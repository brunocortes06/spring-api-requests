package com.bruno.api.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class Price {

    private List<PriceAtHourRange> pricesAtHourRange;

    public List<PriceAtHourRange> getPricesAtHourRange() {
        return pricesAtHourRange;
    }

    public void setPricesAtHourRange(List<PriceAtHourRange> pricesAtHourRange) {
        this.pricesAtHourRange = pricesAtHourRange;
    }

    public BigDecimal getDefaultPrice() {
        return this.pricesAtHourRange.size() > 0 ? this.pricesAtHourRange.get(0).getPrice() : BigDecimal.ZERO;
    }
}
