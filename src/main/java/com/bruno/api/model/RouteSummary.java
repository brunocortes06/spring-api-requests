package com.bruno.api.model;

import java.math.BigDecimal;

public class RouteSummary {
	

    private final Long duration;
    private final Double distance;
    private final BigDecimal totalTollFeeAmount;

    public RouteSummary(Long duration, Double distance, BigDecimal totalTollFeeAmount) {
        this.duration = duration;
        this.distance = distance;
        this.totalTollFeeAmount = totalTollFeeAmount;
    }

    public Long getDuration() {
        return duration;
    }

    public Double getDistance() {
        return distance;
    }

    public BigDecimal getTotalTollFeeAmount() {
        return totalTollFeeAmount;
    }
}
