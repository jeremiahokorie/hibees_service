package com.hibees_service.controller;

import com.hibees_service.core.constant.AppConstant;
import com.hibees_service.core.security.JwtAuthenticationRequest;
import com.hibees_service.core.security.UserTokenState;
import com.hibees_service.domain.response.UserResponse;
import com.hibees_service.dto.ApiResponse;
import com.hibees_service.dto.UserDto;
import com.hibees_service.domain.request.UserRequest;
import com.hibees_service.persistence.entity.User;
import com.hibees_service.service.AuthenticationService;
import com.hibees_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstant.APP_CONTEXT)
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @GetMapping("/test")
    public String authentic(){
        return "authenticated";
    }

    @PostMapping("/signup")
    public ApiResponse<?> createUser(@RequestBody UserRequest users){
        UserDto user = userService.createUser(users);
        return ApiResponse.success(user);
    }

    @PostMapping("login")
    public ApiResponse<UserTokenState> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        UserTokenState authenticationToken = authenticationService.createAuthenticationToken(authenticationRequest);
        return ApiResponse.success(authenticationToken);
    }

    @GetMapping("/users")
    public ApiResponse<?> users(){
        List<User> users = userService.users();
        return ApiResponse.success(users);
    }

    @PutMapping("/update/id")
    public ApiResponse<?> update(@RequestBody UserRequest user, @PathVariable Long id){
       UserResponse usr = userService.update(user,id);
        return ApiResponse.success(usr);
    }

}
