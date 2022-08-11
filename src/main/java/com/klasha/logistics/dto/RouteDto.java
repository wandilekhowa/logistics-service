package com.klasha.logistics.dto;

import com.klasha.logistics.model.Location;

public class RouteDto extends RouteRequestDto {
    private Double distance;
    private Double costOfDelivery;

    public RouteDto() {
    }

    public RouteDto(Location start, Location end) {
        super(start, end);
    }

    public RouteDto(Location start, Location end, Double distance, Double costOfDelivery) {
        super(start, end);
        this.distance = distance;
        this.costOfDelivery = costOfDelivery;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getCostOfDelivery() {
        return costOfDelivery;
    }

    public void setCostOfDelivery(Double costOfDelivery) {
        this.costOfDelivery = costOfDelivery;
    }

    @Override
    public String toString() {
        return "RouteDto{" +
                "distance=" + distance +
                ", costOfDelivery=" + costOfDelivery +
                '}';
    }
}
