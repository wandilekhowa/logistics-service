package com.klasha.logistics.service;

import com.klasha.logistics.common.State;
import com.klasha.logistics.dto.LocationDto;
import com.klasha.logistics.model.Location;
import com.klasha.logistics.repository.LocationRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location createLocation(LocationDto locationRequest) {
        return locationRepository.saveAndFlush(new Location(locationRequest.getName(), locationRequest.getLongitude(),
                locationRequest.getLatitude(), State.Active.toString()));
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Long id) {
        Optional<Location> location = locationRepository.findById(id);
        return location.orElse(null);
    }

    public Location getLocationByName(String name) {
        return locationRepository.findByName(name);
    }

    public Location updateLocation(Location location, LocationDto locationRequest) {
        if(StringUtils.isNotBlank(locationRequest.getName()))
            location.setName(locationRequest.getName());

        if(locationRequest.getLongitude() != null)
            location.setLongitude(locationRequest.getLongitude());

        if(locationRequest.getLatitude() != null)
            location.setLatitude(locationRequest.getLatitude());

        return locationRepository.saveAndFlush(location);
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    public void deleteLocation(String name) {
        locationRepository.deleteByName(name);
    }
}
