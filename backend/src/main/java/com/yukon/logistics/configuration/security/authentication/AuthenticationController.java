package com.yukon.logistics.configuration.security.authentication;

import com.yukon.logistics.model.dto.AuthenticatedUser;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationController {
    
    final AuthenticationService authenticationService;
    
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticatedUser> authenticate(
            @Valid @NonNull @RequestBody final AuthenticationRequest authRequest) {
        final var response = authenticationService.authenticate(authRequest);
        
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, response.getCookieAccessToken().toString())
                .header(HttpHeaders.SET_COOKIE, response.getCookieRefreshToken().toString())
                .body(response.getAuthenticatedUser());
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<AuthenticatedUser> refreshToken(
            @CookieValue(value = "${spring.security.jwt.refresh-cookie-name}") String refreshToken) {
        final var response = authenticationService.tokenRefresh(refreshToken);
        
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, response.getCookieRefreshToken().toString())
                .header(HttpHeaders.SET_COOKIE, response.getCookieAccessToken().toString())
                .body(response.getAuthenticatedUser());
    }
    
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        final var response = authenticationService.logout();
        
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, response.getCookieAccessToken().toString())
                .header(HttpHeaders.SET_COOKIE, response.getCookieRefreshToken().toString())
                .body("Logout successful");
    }
}
