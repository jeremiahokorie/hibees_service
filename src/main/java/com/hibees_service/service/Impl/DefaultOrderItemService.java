package com.hibees_service.service.Impl;

import com.hibees_service.core.exceptions.CustomException;
import com.hibees_service.persistence.admin.Category;
import com.hibees_service.persistence.admin.Location;
import com.hibees_service.persistence.admin.MenuItems;
import com.hibees_service.persistence.repository.CategoryRepository;
import com.hibees_service.persistence.repository.LocationRepository;
import com.hibees_service.persistence.repository.MenuItemRepository;
import com.hibees_service.service.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultOrderItemService implements OrderItemService {
    private CategoryRepository categoryRepository;
    private MenuItemRepository menuItemRepository;
    private LocationRepository location;

    @Override
    public MenuItems createMenu(MenuItems menuItems) {
        Category selectedCategory = categoryRepository.findById(menuItems.getCategory().getId())
                .orElseThrow(() -> new CustomException("Category not found"));
        Location locate = location.findById(menuItems.getLocation().getId()).orElseThrow(()-> new CustomException("location not found"));
        menuItems.setCategory(selectedCategory);
        menuItems.setLocation(locate);
        menuItems.setDate(new Date());
        return menuItemRepository.save(menuItems);
    }


    @Override
    public MenuItems findMenuById(Long id) {
        MenuItems menuItems = menuItemRepository.findById(id)
                .orElseThrow(() -> new CustomException("menu with that Id does not exist"));
        return menuItems;
    }

    @Override
    public List<MenuItems> findAllMenu() {
        return menuItemRepository.findAll();
    }

    @Override
    public MenuItems deleteMenuById(Long menuId) {
        MenuItems menuItems = menuItemRepository.findById(menuId)
                .orElseThrow(() -> new CustomException("Not found"));
        return menuItems;
    }

    @Override
    public MenuItems findByName(String name) {
        MenuItems menuItems = menuItemRepository.findByname(name);
        if (!menuItems.equals(name)){
           throw new CustomException("Menu Name does not exist");
        }

        return menuItems;
    }

    @Override
    public List<MenuItems> getAllItemByLocation(Long id) {
        MenuItems items = menuItemRepository.findById(id)
                .orElseThrow(() -> new CustomException("Not Found: "+id));

        return menuItemRepository.findBylocationId(id);
    }



}
