package com.hibees_service.service.Impl;

import com.google.common.collect.ImmutableMap;
import com.hibees_service.core.util.JwtUtil;
import com.hibees_service.events.EmailNotificationEvent;
import com.hibees_service.persistence.entity.User;
import com.hibees_service.service.PasswordResetService;
import com.hibees_service.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class PasswordResetServiceImpl implements PasswordResetService {

    private UserService userService;
    private JwtUtil jwtUtil;
    private final ApplicationEventPublisher publisher;

    @Override
    public void generatePasswordResetToken(String email) {
        User user = userService.findByemail(email);
        if (user != null) {
            String resetToken = jwtUtil.generatePasswordResetToken(user);
            user.setResetToken(resetToken);
            user.setResetTokenExpiryDateTime(LocalDateTime.now().plusHours(1)); // Token valid for 1 hour
            userService.save(user);
            String resetLink = "http://localhost:8000/api/core/v1/reset-password?token=" + resetToken;
            log.info("Reset link {}", resetLink);
           // publishResetLinkEvent(user.getEmail(), resetLink);

          publisher.publishEvent(new EmailNotificationEvent(this, "password", ImmutableMap.of("recipient", user.getEmail(), "name", user.getFullname(), "url", resetLink)));

        }

    }

//    private void publishResetLinkEvent(String email, String resetLink) {
//        ImmutableMap<String, String> emailParams = ImmutableMap.of(
//                "recipient", email,
//                "resetLink", resetLink
//        );
//
//        publisher.publishEvent(new EmailNotificationEvent(this, "password-reset", emailParams));
//    }


    @Override
    public void resetPassword(String token, String newPassword) {

    }
}
