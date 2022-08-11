package com.klasha.logistics.dto;

import com.klasha.logistics.model.Location;

public class RouteRequestDto {
    private Location start;
    private Location end;

    public RouteRequestDto() {
    }

    public RouteRequestDto(Location start, Location end) {
        this.start = start;
        this.end = end;
    }

    public Location getStart() {
        return start;
    }

    public void setStart(Location start) {
        this.start = start;
    }

    public Location getEnd() {
        return end;
    }

    public void setEnd(Location end) {
        this.end = end;
    }
}
