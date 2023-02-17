package com.yukon.logistics.exceptions;

import java.io.Serial;

/**
 * An exception class that indicates that a requested user was not found.
 * This class extends from the {@link NotFoundException} class.
 */
public class UserNotFoundException extends NotFoundException {
    
    @Serial
    private static final long serialVersionUID = 5824869101380360116L;
    
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
