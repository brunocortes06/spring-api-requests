package com.bruno.api.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Summary {

    private Long duration;
    private Double distance;
    private List<TollFee> tollFees;

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public List<TollFee> getTollFees() {
        return tollFees;
    }

    public void setTollFees(List<TollFee> tollFees) {
        this.tollFees = tollFees;
    }

    public BigDecimal getTotalTollFees() {
        if (this.tollFees.size() > 0)
            return this.tollFees.stream().map(TollFee::getDetaultPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        else
            return BigDecimal.ZERO;
    }
}
