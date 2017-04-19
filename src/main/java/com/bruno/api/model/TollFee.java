package com.bruno.api.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class TollFee {

    private List<Price> prices;

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public BigDecimal getDetaultPrice() {
        return this.prices.size() > 0 ? this.prices.get(0).getDefaultPrice() : BigDecimal.ZERO;
    }
}
