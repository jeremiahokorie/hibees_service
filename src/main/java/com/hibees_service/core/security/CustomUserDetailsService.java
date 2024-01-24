package com.hibees_service.core.security;

import com.hibees_service.persistence.entity.User;
import com.hibees_service.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.naming.ldap.PagedResultsControl;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Configuration
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRoles()));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .accountLocked(false)
                .accountExpired(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Replace this logic with your actual user retrieval logic from the data store
//        // For simplicity, we are creating a hardcoded user with roles "USER" and "ADMIN"
//
//        if ("user".equals(username)) {
//            return buildUserForAuthentication(username, "password", Collections.singletonList("ROLE_USER"));
//        } else if ("admin".equals(username)) {
//            return buildUserForAuthentication(username, "password", Collections.singletonList("ROLE_ADMIN"));
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//    }
//
//    private UserDetails buildUserForAuthentication(String email, String password, Collection<? extends GrantedAuthority> authorities) {
//        return new org.springframework.security.core.userdetails.User(email, password, authorities);
//    }

}



