package com.klasha.logistics.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = Location.TABLE_NAME)
public class Location {

    public static final String TABLE_NAME= "locations";

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name="id")
    private Long id;

    @Column
    private String name;

    @Column
    private Double longitude;

    @Column
    private Double latitude;

    @CreationTimestamp
    @Column(name = "create_date", columnDefinition = "TIMESTAMP", nullable=false, updatable=false, insertable=false)
    private ZonedDateTime createDate;

    @UpdateTimestamp
    @Column(name = "create_date", columnDefinition = "TIMESTAMP", nullable=false, updatable=false, insertable=false)
    private ZonedDateTime lastModifiedDate;

    @Column
    private String state;

    public Location() {
    }

    public Location(String name, Double longitude, Double latitude, String state) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
