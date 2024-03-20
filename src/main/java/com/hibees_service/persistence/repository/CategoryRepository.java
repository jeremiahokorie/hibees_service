package com.hibees_service.persistence.repository;

import com.hibees_service.dto.CategoryDto;
import com.hibees_service.persistence.admin.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category>findByname(String name);
}
