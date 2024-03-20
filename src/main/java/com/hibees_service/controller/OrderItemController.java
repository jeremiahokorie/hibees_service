package com.hibees_service.controller;

import com.hibees_service.core.constant.AppConstant;
import com.hibees_service.dto.ApiResponse;
import com.hibees_service.persistence.admin.MenuItems;
import com.hibees_service.service.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstant.APP_CONTEXT)
@AllArgsConstructor
public class OrderItemController {

    private OrderItemService menuItemService;

    @PostMapping("/create-order")
    public ApiResponse<?> createMenu(@RequestBody MenuItems menuItems){
        MenuItems menuItems1 = menuItemService.createMenu(menuItems);
        return ApiResponse.success(menuItems1);
    }

    @GetMapping("/order/{id}")
    public ApiResponse<?> findMenuById(@PathVariable("id") Long id){
        MenuItems menuItems = menuItemService.findMenuById(id);
        return ApiResponse.success(menuItems);
    }

    @GetMapping("/order")
    public ApiResponse<?> menus(){
        List<MenuItems> menus = menuItemService.findAllMenu();
        return ApiResponse.success(menus);
    }

    @DeleteMapping("/order/{id}")
    public ApiResponse<MenuItems> deleteMenuById(@PathVariable("id") Long menuId){
        MenuItems menuItems = menuItemService.deleteMenuById(menuId);
        return ApiResponse.success(menuItems);
    }

    @GetMapping("/order/{name}")
    public ApiResponse<MenuItems>findByMenuName(@PathVariable("name") String name){
        MenuItems menuItems = menuItemService.findByName(name);
        return ApiResponse.success(menuItems);
    }

    @GetMapping("/location/{locationId}")
    public ResponseEntity<?> findItemByLocation(@PathVariable("locationId") Long locationId){
        List<MenuItems> items = menuItemService.getAllItemByLocation(locationId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

}
