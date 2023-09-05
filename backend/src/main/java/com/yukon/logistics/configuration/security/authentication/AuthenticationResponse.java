package com.yukon.logistics.configuration.security.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yukon.logistics.model.dto.AuthenticatedUser;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseCookie;

@EqualsAndHashCode
@ToString
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    
    @JsonIgnore
    ResponseCookie cookieAccessToken;
    
    @JsonIgnore
    ResponseCookie cookieRefreshToken;
    AuthenticatedUser authenticatedUser;
}
