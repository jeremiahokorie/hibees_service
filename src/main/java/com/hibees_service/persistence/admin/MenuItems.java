package com.hibees_service.persistence.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MenuItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "name", length = 50)
    private String name;
    @Column(name = "description", length = 1000)
    private String description;
    @Column(name = "price")
    private Integer price;
    @Column(name = "quantity")
    private String quantity;
    @Column(name = "created_date")
    private Date date;
    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
}
