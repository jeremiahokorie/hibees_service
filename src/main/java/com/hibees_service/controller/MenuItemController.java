package com.hibees_service.controller;

import com.hibees_service.core.constant.AppConstant;
import com.hibees_service.dto.ApiResponse;
import com.hibees_service.dto.MenuItemDto;
import com.hibees_service.persistence.admin.MenuItems;
import com.hibees_service.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstant.APP_CONTEXT)
@RequiredArgsConstructor
public class MenuItemController {

    private MenuItemService menuItemService;

    @PostMapping("/create-menu")
    public ApiResponse<MenuItems> createMenu(@RequestBody MenuItems menuItems){
        MenuItems menuItems1 = menuItemService.createMenu(menuItems);
        return ApiResponse.success(menuItems1);
    }

    @GetMapping("/menu/{menuId}")
    public ApiResponse<MenuItems> findMenuById(@PathVariable("menuId") Long menuId){
        MenuItems menuItems = menuItemService.findMenuById(menuId);
        return ApiResponse.success(menuItems);
    }

    @GetMapping("/menu")
    public ApiResponse<?> menus(){
        List<MenuItems> menus = menuItemService.findAllMenu();
        return ApiResponse.success(menus);
    }

    @DeleteMapping("/memu/{id}")
    public ApiResponse<MenuItems> deleteMenuById(@PathVariable("menuId") Long menuId){
        MenuItems menuItems = menuItemService.deleteMenuById(menuId);
        return ApiResponse.success(menuItems);
    }

    @GetMapping("/menu/{name}")
    public ApiResponse<MenuItems>findByMenuName(@PathVariable("name") String name){
        MenuItems menuItems = menuItemService.findByName(name);
        return ApiResponse.success(menuItems);
    }

    @GetMapping("/locations/{locationId}")
    public ResponseEntity<?> findItemByLocation(@PathVariable("locationId") Long locationId){
        List<MenuItems> items = menuItemService.getAllItemByLocation(locationId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

}
