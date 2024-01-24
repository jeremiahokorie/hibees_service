package com.hibees_service.core.util;

import org.springframework.security.core.userdetails.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JwtTokenFilter extends OncePerRequestFilter {


    private static final String AUTH_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwtToken = extractJwtToken(request); // Implement this method to extract the token from the request

        if (jwtToken != null) {
            // Validate and set up authentication context based on the token
            Authentication authentication = validateAndAuthenticate(jwtToken); // Implement this method
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwtToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTH_HEADER);

        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }

        return null;
    }

    private Authentication validateAndAuthenticate(String jwtToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("your-secret-key") // Replace with your actual secret key
                    .parseClaimsJws(jwtToken)
                    .getBody();


            String username = claims.getSubject();
            List<String> authorities = (List<String>) claims.get("authorities");

            UserDetails userDetails = buildUserDetails(username, authorities);

            // You can perform additional checks on claims, such as expiration, issuer, etc.

            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        } catch (ExpiredJwtException ex) {
            // Handle token expiration
            return null;
        } catch (MalformedJwtException ex) {
            // Handle invalid token
            return null;
        } catch (Exception ex) {
            // Handle other exceptions
            return null;
        }
    }

    private UserDetails buildUserDetails(String username, List<String> authorities) {
        Collection<? extends GrantedAuthority> grantedAuthorities =
                authorities.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        return new User(username, "", grantedAuthorities);
    }
}
