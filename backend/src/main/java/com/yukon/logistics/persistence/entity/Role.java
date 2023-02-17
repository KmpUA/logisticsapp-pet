package com.yukon.logistics.persistence.entity;

/**
 * Roles that are used for the implementation of security
 * and can be used in the Role in the future.
 */
public enum Role {
    /**
     * Specific trucker role.
     */
    TRUCKER,
    /**
     * Specific customer role.
     */
    CUSTOMER,
    /**
     * Specific dispatcher role.
     */
    DISPATCHER,
    /**
     * Privilege role.
     */
    ADMIN
}
