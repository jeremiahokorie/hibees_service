package com.hibees_service.config;


import com.hibees_service.core.util.integrations.InfoBipSendEmailRequest;
import com.hibees_service.core.util.integrations.InfoBipSendEmailResponse;
import com.hibees_service.core.util.integrations.InfoBipSendSmsRequest;
import com.hibees_service.core.util.integrations.InfoBipSendSmsResponse;
import com.hibees_service.service.InfoBipIntegration;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public InfoBipIntegration infoBipIntegration() {
        return new InfoBipIntegration() {
            @Override
            public ResponseEntity<InfoBipSendEmailResponse> sendEmail(InfoBipSendEmailRequest request) {
                return null;
            }

            @Override
            public ResponseEntity<InfoBipSendSmsResponse> sendSms(InfoBipSendSmsRequest request) {
                return null;
            }
        };
    }


}
