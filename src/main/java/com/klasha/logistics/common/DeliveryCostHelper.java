package com.klasha.logistics.common;

import com.klasha.logistics.dto.RouteDto;
import com.klasha.logistics.model.Location;

public class DeliveryCostHelper {

    static final double costPerKm = 1.00;

    public static RouteDto calculateCost(Location start, Location end) {
        Double distance = DistanceHelper.calculateDistanceBetweenPoints(start.getLongitude(), start.getLatitude(), end.getLongitude(), end.getLongitude());
        Double costOfDelivery = costPerKm * distance;

        return new RouteDto(start, end, distance, costOfDelivery);
    }
}
