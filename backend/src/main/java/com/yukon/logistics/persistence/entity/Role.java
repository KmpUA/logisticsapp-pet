package com.yukon.logistics.persistence.entity;

/*
 * Roles that are used for the implementation of security
 * and can be used in the Role in the future.
 */
public enum Role {
    /*
     * Default role.
     */
    USER,
    /*
     * Specific role.
     */
    TRUCKER,
    /*
     * Specific trucker role.
     */
    CUSTOMER,
    /*
     * Specific dispatcher role.
     */
    DISPATCHER,
    /*
     * Privilege role.
     */
    ADMIN
}