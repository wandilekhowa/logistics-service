package com.klasha.logistics.repository;

import com.klasha.logistics.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByName(String name);
    void deleteByName(String name);
}
