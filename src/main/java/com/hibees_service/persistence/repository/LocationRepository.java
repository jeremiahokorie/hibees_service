package com.hibees_service.persistence.repository;

import com.hibees_service.persistence.admin.Category;
import com.hibees_service.persistence.admin.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
   Optional<Location> findByname(String name);
}
