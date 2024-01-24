package com.hibees_service.controller;

import com.hibees_service.core.constant.AppConstant;
import com.hibees_service.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstant.APP_CONTEXT)
@RequiredArgsConstructor
public class ForgotPasswordController {

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/generate-token")
    public ResponseEntity<String> generatePasswordResetToken(@RequestParam String email) {
        passwordResetService.generatePasswordResetToken(email);
        return ResponseEntity.ok("Password reset token generated and sent to the user's email.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        passwordResetService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Password reset successfully.");
    }
}
