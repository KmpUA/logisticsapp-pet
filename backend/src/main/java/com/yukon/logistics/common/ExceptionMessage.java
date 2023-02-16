package com.yukon.logistics.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@PropertySource("classpath:exception-message.properties")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExceptionMessage {
    
    @Value("${internal.server.error.message}")
    private String internalServerErrorMessage;
    
    @Value("${unauthorized.error.message}")
    private String unauthorizedErrorMessage;
    
    @Value("${access.denied.error.message}")
    private String accessDeniedErrorMessage;
    
    @Value("${bad.request.error.message}")
    private String badRequestErrorMessage;
    
    @Value("${not.found.error.message}")
    private String notFoundErrorMessage;
}
