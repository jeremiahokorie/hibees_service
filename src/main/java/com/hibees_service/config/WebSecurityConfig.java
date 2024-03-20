package com.hibees_service.config;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import com.hibees_service.core.security.CustomCorsFilter;
import com.hibees_service.core.security.JwtTokenFilterConfigurer;
import com.hibees_service.core.security.RestAccessDeniedHandler;
import com.hibees_service.core.security.RestAuthenticationEntryPoint;
import com.hibees_service.core.util.JwtTokenFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import com.hibees_service.core.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtUtil jwtUtil;
    private final RestAccessDeniedHandler accessDeniedHandler;
    private final RestAuthenticationEntryPoint authenticationEntryPoint;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.httpFirewall(allowUrlEncodedSlashHttpFirewall())
                .ignoring()
                .requestMatchers("/v1/api-docs")
                .requestMatchers("/v2/api-docs")
                .requestMatchers("/swagger-resources/**")
                .requestMatchers("/swagger-ui/index.html")
                .requestMatchers("/configuration/**")
                .requestMatchers("/webjars/**")
                .requestMatchers("/images/**")
                .requestMatchers("/css/**")
                .requestMatchers("/assets/**")
                .requestMatchers("/js/**")
                .requestMatchers("/fonts/**")
                .requestMatchers("/favicon.ico")
                .requestMatchers("/public/**")
                .requestMatchers("/actuator/**");
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }

    @Component
    public static class SwaggerConfiguration implements ApplicationListener<ApplicationPreparedEvent> {

        @Override
        public void onApplicationEvent(final ApplicationPreparedEvent event) {
            ConfigurableEnvironment environment = event.getApplicationContext().getEnvironment();
            Properties props = new Properties();
            props.put("springdoc.swagger-ui.path", swaggerPath());
            environment.getPropertySources()
                    .addFirst(new PropertiesPropertySource("programmatically", props));
        }

        private String swaggerPath() {
            return "/myproject"; // TODO: implement your logic here.
        }
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("ADMIN");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/v2/auth/**", "/api/core/v1/login", "/api/core/v1/signup").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS).permitAll() // Allow CORS preflight requests
                                .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }


}