package com.hibees_service.service;

import com.hibees_service.core.security.JwtAuthenticationRequest;
import com.hibees_service.core.security.UserTokenState;
import org.springframework.context.annotation.Bean;

public interface AuthenticationService {

    UserTokenState createAuthenticationToken(JwtAuthenticationRequest authenticationRequest);
}
