package com.hibees_service.dto;

import com.hibees_service.persistence.entity.User;
import lombok.Data;

@Data
public class UserDto {
    private String fullname;
    private String phonenumber;
    private String email;
    private String address;

    public static UserDto fromEntity(User user){
        UserDto userDto = new UserDto();
        userDto.setFullname(user.getFullname());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setPhonenumber(user.getPhonenumber());
        return userDto;
    }
}
