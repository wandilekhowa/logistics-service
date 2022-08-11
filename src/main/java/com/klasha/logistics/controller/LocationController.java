package com.klasha.logistics.controller;

import com.klasha.logistics.common.Swagger2Config;
import com.klasha.logistics.dto.LocationDto;
import com.klasha.logistics.model.Location;
import com.klasha.logistics.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/locations")
@Api(tags = {Swagger2Config.TAG_2}, value = "Administer location services")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @ApiOperation(value = "Create Location")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created new Location", response = Location.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 502, message = "Gateway error")
    })
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Location> registerLocation(@RequestBody LocationDto request) {
        if (request == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(request.getName()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(request.getLongitude() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(request.getLatitude() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Location location = locationService.getLocationByName(request.getName());
        if (location != null)
            return new ResponseEntity<>(locationService.updateLocation(location, request), HttpStatus.OK);

        return new ResponseEntity<>(locationService.createLocation(request), HttpStatus.OK);
    }

    @ApiOperation(value = "Update Location")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated Location", response = Location.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 502, message = "Gateway error")
    })
    @PatchMapping(value = "/{locationId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Location> updateLocation(@PathVariable Long locationId, @RequestBody LocationDto request) {
        if (request == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        Location location = locationService.getLocationById(locationId);
        if (location != null)
            return new ResponseEntity<>(locationService.updateLocation(location, request), HttpStatus.OK);

        return new ResponseEntity<>(locationService.createLocation(request), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Location")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted Location", response = Location.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 502, message = "Gateway error")
    })
    @DeleteMapping(value = "/{locationId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long locationId) {
        if (locationId == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Location location = locationService.getLocationById(locationId);
        if (location == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        locationService.deleteLocation(locationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
