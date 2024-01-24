package com.hibees_service.persistence.repository;

import com.hibees_service.persistence.entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {

    List<Favorites> findByuserId(Long id);
}
