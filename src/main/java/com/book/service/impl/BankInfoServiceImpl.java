package com.book.service.impl;

import com.book.domain.UserInfo;
import com.book.repository.UserInfoRepository;
import com.book.service.BankInfoService;
import com.book.domain.BankInfo;
import com.book.repository.BankInfoRepository;
import com.book.web.rest.errors.BadRequestAlertException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing BankInfo.
 */
@Service
@Transactional
public class BankInfoServiceImpl implements BankInfoService {

    private final Logger log = LoggerFactory.getLogger(BankInfoServiceImpl.class);

    private final BankInfoRepository bankInfoRepository;

    private final UserInfoRepository userInfoRepository;

    public BankInfoServiceImpl(BankInfoRepository bankInfoRepository,UserInfoRepository userInfoRepository) {
        this.bankInfoRepository = bankInfoRepository;
        this.userInfoRepository = userInfoRepository;
    }

    /**
     * Save a bankInfo.
     *
     * @param bankInfo the entity to save
     * @return the persisted entity
     */
    @Override
    public BankInfo save(BankInfo bankInfo) {
        log.debug("Request to save BankInfo : {}", bankInfo);
        UserInfo userInfo =  userInfoRepository.findUserInfoByUserId(bankInfo.getUserId());
        if(userInfo == null){
            throw new BadRequestAlertException("主人，没有查到遮天人物啊","","400");
        }
        return bankInfoRepository.save(bankInfo);
    }

    /**
     * Get all the bankInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BankInfo> findAll(Pageable pageable) {
        log.debug("Request to get all BankInfos");
        return bankInfoRepository.findAll(pageable);
    }


    /**
     * Get one bankInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BankInfo> findOne(Long id) {
        log.debug("Request to get BankInfo : {}", id);
        return bankInfoRepository.findById(id);
    }

    /**
     * Delete the bankInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BankInfo : {}", id);
        bankInfoRepository.deleteById(id);
    }

    /**
     * 根据条件获取一条记录
     *
     * @param bankId
     * @return
     */
    @Override
    public Optional<BankInfo> findBankInfoByBankId(String bankId) {
        if (StringUtils.isBlank(bankId)){
            throw new BadRequestAlertException("主人,没有查询条件啊","","400");
        }
        Optional<BankInfo> bankInfo = bankInfoRepository.findBankInfoByBankId(bankId);
        if (bankInfo.isPresent()){
            throw new BadRequestAlertException("主人，没有查到数据啊","","");
        }
        return  bankInfo;
    }

    /**
     * 获取集合
     */
    @Override
    public List<BankInfo> findAll() {
        return bankInfoRepository.findAll();
    }
}
