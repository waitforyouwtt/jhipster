package com.book.service;

import com.book.domain.BankInfo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing BankInfo.
 */
public interface BankInfoService {

    /**
     * Save a bankInfo.
     *
     * @param bankInfo the entity to save
     * @return the persisted entity
     */
    BankInfo save(BankInfo bankInfo);

    /**
     * Get all the bankInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BankInfo> findAll(Pageable pageable);


    /**
     * Get the "id" bankInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BankInfo> findOne(Long id);

    /**
     * Delete the "id" bankInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * 根据条件获取一条记录
     * @param bankId
     * @return
     */
    Optional<BankInfo> findBankInfoByBankId(String bankId);
    /**
     * 获取集合
     */
    List<BankInfo> findAll();
}
