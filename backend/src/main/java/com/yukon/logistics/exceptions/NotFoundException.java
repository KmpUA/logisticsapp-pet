package com.yukon.logistics.exceptions;

import java.io.Serial;

/**
 * Exception class representing HTTP 404(Not found).
 */
public class NotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 887841124424086275L;
    
    public NotFoundException(String msg) {
        super(msg);
    }
}
