package com.hibees_service.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hibees_service.core.exceptions.CustomException;
import com.hibees_service.domain.response.UserResponse;
import com.hibees_service.dto.UserDto;
import com.hibees_service.core.util.enums.UserRole;
import com.hibees_service.events.EmailNotificationEvent;
import com.hibees_service.persistence.entity.User;
import com.hibees_service.domain.request.UserRequest;
import com.hibees_service.persistence.repository.UserRepository;
import com.hibees_service.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.google.common.collect.ImmutableMap;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class DefaultUserService implements UserService {

    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationEventPublisher publisher;

    private final ObjectMapper objectMapper;


    @Override
    public UserDto createUser(UserRequest users) {
        User user = User.builder()
                .fullname(users.getFullname())
                .email(users.getEmail())
                .phonenumber(users.getPhonenumber())
                .address(users.getAddress())
                .roles(Collections.singleton(UserRole.USER))
                .password(passwordEncoder.encode(users.getPassword()))
                .createdDate(new Date())
                .build();

        if (userRepository.findByEmail(users.getEmail()).isPresent()) {
            throw new CustomException("User with this email " + users.getEmail() +". Already exist, Try another email address");
        }

        userRepository.save(user);
        publisher.publishEvent(new EmailNotificationEvent(this, "welcome", ImmutableMap.of("recipient", user.getEmail(), "name", user.getFullname())));
        return UserDto.fromEntity(user);
    }

    @Override
    public User findByemail(String email) {
       return userRepository.findByemail(email).orElseThrow(() -> new EntityNotFoundException("Email does not exist"));

    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> users() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse update(UserRequest user, Long id) {
        User update = userRepository.findById(id).orElseThrow(() -> new CustomException("User not found"));
        update.setFullname(user.getFullname());
        update.setEmail(user.getEmail());
        update.setAddress(user.getAddress());
        update.setPhonenumber(user.getPhonenumber());
        update.setUpdatedAt(user.getUpdatedAt());
        userRepository.save(update);
        return UserResponse.builder()
                .fullname(user.getFullname())
                .email(user.getEmail())
                .phonenumber(user.getPhonenumber())
                .address(user.getAddress())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
