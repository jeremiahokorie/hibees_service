package com.hibees_service.core.constant;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class AppConstant {
    public static final String APP_CONTEXT = "/api/core/v1";
    public static final String resetPasswordTemplate = "reset-password.ftl";
}
