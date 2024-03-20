package com.hibees_service.service.Impl;

import com.hibees_service.core.exceptions.CustomException;
import com.hibees_service.persistence.admin.Location;
import com.hibees_service.persistence.repository.LocationRepository;
import com.hibees_service.service.LocationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultLocationService implements LocationService {

    private LocationRepository locationRepository;

    @Override
    public Location createLocation(Location location) {

        if (locationRepository.findByaddress(location.getAddress()).isPresent()){
            throw new CustomException("Location Already exist, Try another location");
        }
        locationRepository.save(location);
        return location;
    }

    @Override
    public Location findLocationById(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new CustomException("NOT FOUND"));
        return location;
    }
}
