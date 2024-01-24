package com.hibees_service.service.Impl;

import com.hibees_service.core.exceptions.CustomException;
import com.hibees_service.dto.MenuItemDto;
import com.hibees_service.persistence.admin.Category;
import com.hibees_service.persistence.admin.MenuItems;
import com.hibees_service.persistence.repository.CategoryRepository;
import com.hibees_service.persistence.repository.MenuItemRepository;
import com.hibees_service.service.MenuItemService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultMenuItemService implements MenuItemService {
    private CategoryRepository categoryRepository;
    private MenuItemRepository menuItemRepository;

    @Override
    public MenuItems createMenu(MenuItems menuItems) {
        Category selectedCategory = categoryRepository.findById(menuItems.getCategory().getCategoryId())
                .orElseThrow(() -> new CustomException("Category not found"));
        menuItems.setCategory(selectedCategory);
       // menuItems.setLocation(location);
        menuItems.setDate(new Date());
        return menuItemRepository.save(menuItems);
    }


    @Override
    public MenuItems findMenuById(Long menuId) {
        MenuItems menuItems = menuItemRepository.findById(menuId)
                .orElseThrow(() -> new CustomException("Not found"));
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
