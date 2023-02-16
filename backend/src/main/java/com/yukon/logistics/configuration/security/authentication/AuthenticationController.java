package com.yukon.logistics.configuration.security.authentication;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    
    AuthenticationService authenticationService;
    
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @NonNull @RequestBody AuthenticationRequest authRequest) {
        
        return ResponseEntity.ok(authenticationService.authenticate(authRequest));
    }
    
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        
        return ResponseEntity.ok("q");
    }
}
