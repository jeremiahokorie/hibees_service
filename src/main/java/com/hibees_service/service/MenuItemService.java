package com.hibees_service.service;

import com.hibees_service.dto.MenuItemDto;
import com.hibees_service.persistence.admin.MenuItems;

import java.util.List;

public interface MenuItemService {
    MenuItems createMenu(MenuItems menuItems);

    MenuItems findMenuById(Long menuId);

    List<MenuItems> findAllMenu();

    MenuItems deleteMenuById(Long menuId);

    MenuItems findByName(String name);

    List<MenuItems> getAllItemByLocation(Long locationId);
}
