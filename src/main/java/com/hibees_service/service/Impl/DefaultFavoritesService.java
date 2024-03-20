package com.hibees_service.service.Impl;

import com.hibees_service.core.exceptions.CustomException;
import com.hibees_service.persistence.admin.MenuItems;
import com.hibees_service.persistence.entity.Favorites;
import com.hibees_service.persistence.entity.User;
import com.hibees_service.persistence.repository.FavoritesRepository;
import com.hibees_service.persistence.repository.MenuItemRepository;
import com.hibees_service.persistence.repository.UserRepository;
import com.hibees_service.service.FavoritesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultFavoritesService implements FavoritesService {

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public void addToFavorites(Long userId, Long menuId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found"));

        Favorites userFavorite = new Favorites();
        userFavorite.setUser(user);

        if (menuId != null) {
            MenuItems menuItem = menuItemRepository.findById(menuId)
                    .orElseThrow(() -> new CustomException("Menu item not found"));
            userFavorite.setMenuItem(menuItem);
        }
        favoritesRepository.save(userFavorite);
    }
    public List<Favorites> getUserFavorites(Long userId) {
        return favoritesRepository.findByuserId(userId);
    }
    public void removeFromFavorites(Long id) {
        Favorites userFavorite = favoritesRepository.findById(id)
                .orElseThrow(() -> new CustomException("Favorite not found"));

        favoritesRepository.delete(userFavorite);
    }
}
