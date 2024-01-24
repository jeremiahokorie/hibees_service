package com.hibees_service.persistence.repository;

import com.hibees_service.persistence.admin.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByname(String name);

}
