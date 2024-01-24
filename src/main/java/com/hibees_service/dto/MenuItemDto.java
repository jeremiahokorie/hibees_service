package com.hibees_service.dto;

import com.hibees_service.persistence.admin.Category;
import com.hibees_service.persistence.admin.Location;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import java.util.Date;

@Data
public class MenuItemDto {
    private String name;
    private String description;
    private Integer price;
    private String quantity;
    private Date date;
    private String image;
    private Category category;
    private Location location;


}
