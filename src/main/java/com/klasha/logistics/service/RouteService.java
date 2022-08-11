package com.klasha.logistics.service;

import com.klasha.logistics.common.DeliveryCostHelper;
import com.klasha.logistics.dto.RouteDto;
import com.klasha.logistics.dto.RouteRequestDto;
import com.klasha.logistics.model.Location;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    public RouteDto getRoute(RouteRequestDto routeRequestDto) {
        return DeliveryCostHelper.calculateCost(routeRequestDto.getStart(), routeRequestDto.getEnd());
    }

}
