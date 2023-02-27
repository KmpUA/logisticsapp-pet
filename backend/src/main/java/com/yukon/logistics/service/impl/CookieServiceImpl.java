package com.yukon.logistics.service.impl;

import com.yukon.logistics.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

@Service
@Slf4j
public class CookieServiceImpl implements CookieService {
    
    @Override
    public String getCookieValueFromRequest(@NonNull final HttpServletRequest request,
                                            @NonNull final String name) {
        final var cookie = WebUtils.getCookie(request, name);
        
        if (cookie == null) {
            log.error("Unable resolve token" +
                    " from the cookies. It can be null or invalid name.");
            return null;
        }
        
        return cookie.getValue();
    }
    
    @Override
    public ResponseCookie createCookie(@NonNull final String name,
                                       @NonNull final String value,
                                       @NonNull final String path,
                                       @NonNull final Long lifetimeInSeconds) {
        return ResponseCookie
                .from(name, value)
                .path(path)
                .maxAge(lifetimeInSeconds)
                .httpOnly(true)
                .secure(false)
                .sameSite(Cookie.SameSite.STRICT.name())
                .build();
    }
    
    @Override
    public ResponseCookie deleteCookie(@NonNull final String name,
                                       @NonNull final String path) {
        return createCookie(name, "", path, 0L);
    }
}
