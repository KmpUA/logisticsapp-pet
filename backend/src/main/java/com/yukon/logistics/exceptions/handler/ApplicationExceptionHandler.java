package com.yukon.logistics.exceptions.handler;

import com.yukon.logistics.common.ExceptionMessage;
import com.yukon.logistics.model.dto.ErrorMessage;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    
    private final ExceptionMessage exceptionMessage;
    
    /**
     * Handle AuthenticationExceptions.
     */
    @ExceptionHandler({AuthenticationException.class, JwtException.class})
    public ResponseEntity<ErrorMessage> handleAuthenticationException(
            @NonNull final HttpServletRequest request,
            @NonNull final Exception exception) {
        
        log.error("Exception was thrown due authentication:", exception);
        
        final var message = ErrorMessage.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .date(new Date())
                .description(exceptionMessage.getUnauthorizedErrorMessage())
                .url(request.getRequestURL().toString())
                .build();
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }
    
    /**
     * Handle all other exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleAllExceptions(
            @NonNull final Exception exception,
            @NonNull final HttpServletRequest request) {
        
        log.error("Exception was thrown due unknown exception:", exception);
        
        final var responseStatus =
                exception.getClass().getAnnotation(ResponseStatus.class);
        final var status =
                responseStatus == null ? HttpStatus.INTERNAL_SERVER_ERROR : responseStatus.value();
        final var message = ErrorMessage.builder()
                .status(status.value())
                .date(new Date())
                .description(exceptionMessage.getInternalServerErrorMessage())
                .url(request.getRequestURL().toString())
                .build();
        
        return ResponseEntity.status(status).body(message);
    }
}
