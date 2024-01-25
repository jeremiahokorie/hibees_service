package com.hibees_service.service.Impl;

import com.hibees_service.core.security.JwtAuthenticationRequest;
import com.hibees_service.core.security.UserTokenState;
import com.hibees_service.core.util.JwtUtil;
import com.hibees_service.persistence.entity.User;
import com.hibees_service.persistence.repository.UserRepository;
import com.hibees_service.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import static com.hibees_service.core.constant.AppDetails.EXPIRE_IN;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserTokenState createAuthenticationToken(JwtAuthenticationRequest authenticationRequest) {
        User user = userRepository
                .findByEmail(authenticationRequest.getEmail()).orElseThrow(()
                        -> new BadCredentialsException("Bad credentials"));
        boolean match = passwordEncoder.matches(authenticationRequest.getPassword(),user.getPassword());
        if (!match){
            throw new BadCredentialsException("Bad credentials");
        }

        //token creation
        user.setLastLoginDate(new Date());
        userRepository.save(user);
        String jwtToken =jwtUtil.createToken(user.getEmail(),user.getRoles());
        return new UserTokenState(jwtToken, EXPIRE_IN);
    }
}
