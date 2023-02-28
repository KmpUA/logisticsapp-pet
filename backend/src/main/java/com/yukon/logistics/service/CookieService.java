package com.yukon.logistics.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.http.ResponseCookie;

public interface CookieService {
    
    /**
     * Extracts cookie from request by name.
     *
     * @param request to extract the cookie from
     * @param name    the cookie name whose value we want to retrieve
     * @return value by cookie name if it exists
     */
    String getCookieValueFromRequest(@NonNull final HttpServletRequest request,
                                     @NonNull final String name);
    
    /**
     * Creates cookie based on the passed parameters.
     *
     * @param name              specify the cookie name
     * @param value             specify the cookie name-related value
     * @param path              specify the path for which this cookie is sent.
     * @param lifetimeInSeconds specify the cookie lifetime
     * @return created ResponseCookie by passed parameters
     * @see ResponseCookie
     */
    ResponseCookie createCookie(@NonNull final String name,
                                @NonNull final String value,
                                @NonNull final String path,
                                @NonNull final Long lifetimeInSeconds);
    
    /**
     * It's removing cookie with passed name from browser.
     * It forms a new ResponseCookie with the passed name,
     * but with a maxAge value set to 0, and so for remove
     * this cookie from the browser you need to pass the returned
     * object into the response
     *
     * @param name cookie name to delete
     * @return ResponseCookie
     */
    ResponseCookie deleteCookie(@NonNull final String name,
                                @NonNull final String path);
}
