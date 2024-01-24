package com.hibees_service.service;

import com.hibees_service.domain.response.UserResponse;
import com.hibees_service.dto.UserDto;
import com.hibees_service.persistence.entity.User;
import com.hibees_service.domain.request.UserRequest;

import java.util.List;

public interface UserService {

    UserDto createUser(UserRequest users);

    User findByemail(String email);

    void save(User user);

    List<User> users();

    UserResponse update(UserRequest user, Long id);
}
