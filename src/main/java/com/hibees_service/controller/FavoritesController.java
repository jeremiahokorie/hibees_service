package com.hibees_service.controller;

import com.hibees_service.core.constant.AppConstant;
import com.hibees_service.domain.request.AddToFavoritesRequest;
import com.hibees_service.persistence.entity.Favorites;
import com.hibees_service.service.FavoritesService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstant.APP_CONTEXT)
@AllArgsConstructor
public class FavoritesController {

    private FavoritesService favoritesService;

    @PostMapping("/favorite/addToFavorites")
    public ResponseEntity<String> addToFavorites(@RequestBody AddToFavoritesRequest request) {
        favoritesService.addToFavorites(request.getUser().getId(), request.getMenuItem().getMenuId());
        return ResponseEntity.ok("Added to favorites successfully.");
    }

    @GetMapping("/favorite/getUserFavorites")
    public ResponseEntity<List<Favorites>> getUserFavorites(@RequestParam Long userId) {
        List<Favorites> favorites = favoritesService.getUserFavorites(userId);
        return ResponseEntity.ok(favorites);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeFromFavorites(@PathVariable Long id) {
        favoritesService.removeFromFavorites(id);
        return ResponseEntity.ok("Removed from favorites successfully.");
    }
}
