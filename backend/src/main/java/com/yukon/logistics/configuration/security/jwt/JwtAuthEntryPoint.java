package com.yukon.logistics.configuration.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yukon.logistics.common.ExceptionMessage;
import com.yukon.logistics.model.dto.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    
    ExceptionMessage exceptionMessage;
    ObjectMapper objectMapper;
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        
        final var message = ErrorMessage.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .date(new Date())
                .description(exceptionMessage.getUnauthorizedErrorMessage())
                .url(request.getRequestURL().toString())
                .build();
        
        response.getWriter().println(objectMapper.writeValueAsString(message));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
