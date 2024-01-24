package com.hibees_service.service;

import com.hibees_service.persistence.entity.Favorites;

import java.util.List;

public interface FavoritesService {
    void addToFavorites(Long userId, Long menuId);

    List<Favorites> getUserFavorites(Long userId);

    void removeFromFavorites(Long id);
}
