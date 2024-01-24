package com.hibees_service.service;

import com.hibees_service.persistence.admin.Location;
import org.springframework.stereotype.Service;

@Service
public interface LocationService {
    Location createLocation(Location location);

    Location findLocationById(Long id);
}
