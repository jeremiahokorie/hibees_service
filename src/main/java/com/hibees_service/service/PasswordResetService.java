package com.hibees_service.service;

public interface PasswordResetService {
    void generatePasswordResetToken(String email);
    void resetPassword(String token, String newPassword);
}
