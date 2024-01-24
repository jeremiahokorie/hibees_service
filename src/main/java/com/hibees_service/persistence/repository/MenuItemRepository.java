package com.hibees_service.persistence.repository;

import com.hibees_service.persistence.admin.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItems, Long> {
    MenuItems findByname(String name);

    List<MenuItems>findBylocationId(Long id);
}
