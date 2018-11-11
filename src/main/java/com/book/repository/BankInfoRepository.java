package com.book.repository;

import com.book.domain.BankInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the BankInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankInfoRepository extends JpaRepository<BankInfo, Long> {
    /**
     * 根据bankId 获取一条记录
     * @param bankId
     * @return
     */
    Optional<BankInfo> findBankInfoByBankId(String bankId);
}
