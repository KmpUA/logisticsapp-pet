package com.yukon.logistics.configuration.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yukon.logistics.common.ApplicationConstants;
import com.yukon.logistics.model.dto.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
@Component
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    
    ObjectMapper objectMapper;
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        log.error("Some error occurred while authorize: " + authException);
    
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        
        final var message = ErrorMessage.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .date(new Date())
                .description(ApplicationConstants.ErrorMassage.UNAUTHORIZED_ERROR_MESSAGE)
                .url(request.getRequestURL().toString())
                .build();
        
        response.getWriter().println(objectMapper.writeValueAsString(message));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
