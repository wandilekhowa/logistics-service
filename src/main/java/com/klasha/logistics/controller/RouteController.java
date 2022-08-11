package com.klasha.logistics.controller;

import com.klasha.logistics.common.Swagger2Config;
import com.klasha.logistics.dto.RouteDto;
import com.klasha.logistics.dto.RouteRequestDto;
import com.klasha.logistics.model.Location;
import com.klasha.logistics.service.LocationService;
import com.klasha.logistics.service.RouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/routes")
@Api(tags = {Swagger2Config.TAG_3}, value = "Administer location services")
public class RouteController {

    private RouteService routeService;
    private LocationService locationService;

    public RouteController(RouteService routeService, LocationService locationService) {
        this.routeService = routeService;
        this.locationService = locationService;
    }

    @ApiOperation(value = "Get optimal route")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved route", response = RouteDto.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 502, message = "Gateway error")
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<RouteDto> getRoute(@RequestBody RouteRequestDto routeRequestDto) {
        if (routeRequestDto == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<Location> locations = locationService.getAllLocations();
        if(locations.size() < 3)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        RouteDto route = routeService.getRoute(routeRequestDto);
        if (route == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(route, HttpStatus.OK);
    }
}
