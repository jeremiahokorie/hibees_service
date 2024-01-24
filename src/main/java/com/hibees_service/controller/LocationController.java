package com.hibees_service.controller;


import com.hibees_service.core.constant.AppConstant;
import com.hibees_service.dto.ApiResponse;
import com.hibees_service.persistence.admin.Location;
import com.hibees_service.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstant.APP_CONTEXT)
@RequiredArgsConstructor
public class LocationController {

    private LocationService locationService;

    @PostMapping("/create")
    public ApiResponse<?> createLocation(@RequestBody Location location){
        Location locations = locationService.createLocation(location);
        return ApiResponse.success(locations);
    }

    @GetMapping("/location/{id}")
    public ApiResponse<Location> findLocationById(@PathVariable("id") Long id){
        Location location = locationService.findLocationById(id);
        return ApiResponse.success(location);
    }

}
