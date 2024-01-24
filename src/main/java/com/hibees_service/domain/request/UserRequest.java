package com.hibees_service.domain.request;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class UserRequest {
    private String fullname;
    private String phonenumber;
    private String email;
    private String address;
    private String password;
    private Date createdDate;
    private Date updatedAt;

}
