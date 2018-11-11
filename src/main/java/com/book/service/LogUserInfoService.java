package com.book.service;

import com.book.domain.LogUserInfo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing LogUserInfo.
 */
public interface LogUserInfoService {

    /**
     * Save a logUserInfo.
     *
     * @param logUserInfo the entity to save
     * @return the persisted entity
     */
    LogUserInfo save(LogUserInfo logUserInfo);

    /**
     * Get all the logUserInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LogUserInfo> findAll(Pageable pageable);


    /**
     * Get the "id" logUserInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LogUserInfo> findOne(Long id);

    /**
     * Delete the "id" logUserInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
