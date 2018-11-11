package com.book.service.impl;

import com.book.service.LogUserInfoService;
import com.book.domain.LogUserInfo;
import com.book.repository.LogUserInfoRepository;
import com.book.web.rest.errors.BadRequestAlertException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing LogUserInfo.
 */
@Service
@Transactional
public class LogUserInfoServiceImpl implements LogUserInfoService {

    private final Logger log = LoggerFactory.getLogger(LogUserInfoServiceImpl.class);

    private final LogUserInfoRepository logUserInfoRepository;

    public LogUserInfoServiceImpl(LogUserInfoRepository logUserInfoRepository) {
        this.logUserInfoRepository = logUserInfoRepository;
    }

    /**
     * Save a logUserInfo.
     *
     * @param logUserInfo the entity to save
     * @return the persisted entity
     */
    @Override
    public LogUserInfo save(LogUserInfo logUserInfo) {
        log.debug("Request to save LogUserInfo : {}", logUserInfo);
        if(StringUtils.isBlank(logUserInfo.getUserName())){
           throw new BadRequestAlertException("400","","用户名不能为空");
        }
        return logUserInfoRepository.save(logUserInfo);
    }

    /**
     * Get all the logUserInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LogUserInfo> findAll(Pageable pageable) {
        log.debug("Request to get all LogUserInfos");
        return logUserInfoRepository.findAll(pageable);
    }


    /**
     * Get one logUserInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LogUserInfo> findOne(Long id) {
        log.debug("Request to get LogUserInfo : {}", id);
        return logUserInfoRepository.findById(id);
    }

    /**
     * Delete the logUserInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LogUserInfo : {}", id);
        logUserInfoRepository.deleteById(id);
    }
}
