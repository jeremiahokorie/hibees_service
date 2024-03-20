package com.hibees_service.service;

import com.hibees_service.persistence.admin.MenuItems;

import java.util.List;

public interface OrderItemService {
    MenuItems createMenu(MenuItems menuItems);

    MenuItems findMenuById(Long id);

    List<MenuItems> findAllMenu();

    MenuItems deleteMenuById(Long menuId);

    MenuItems findByName(String name);

    List<MenuItems> getAllItemByLocation(Long locationId);
}
