package com.hibees_service.domain.request;

import com.hibees_service.persistence.admin.MenuItems;
import com.hibees_service.persistence.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AddToFavoritesRequest {

    private User user;

    private MenuItems menuItem;
}
