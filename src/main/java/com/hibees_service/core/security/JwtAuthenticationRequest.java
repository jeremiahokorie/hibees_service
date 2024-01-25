package com.hibees_service.core.security;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtAuthenticationRequest implements Serializable {
    private String email;
    private String password;
}
