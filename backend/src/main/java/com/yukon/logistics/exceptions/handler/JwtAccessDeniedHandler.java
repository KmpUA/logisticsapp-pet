package com.yukon.logistics.exceptions.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yukon.logistics.common.ApplicationConstants;
import com.yukon.logistics.model.dto.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    
    private final ObjectMapper objectMapper;
    
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        log.error("Some error occurred while authorize, access denied: " + accessDeniedException);
        
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
