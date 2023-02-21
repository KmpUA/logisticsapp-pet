package com.yukon.logistics.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Page service for create REST Pagination
 */
public interface PageableService<T> {
    
    /**
     * Find entities looking for a certain number of users
     *
     * @param pageable data about pages
     * @return page
     */
    Page<T> findAll(Pageable pageable);
}
