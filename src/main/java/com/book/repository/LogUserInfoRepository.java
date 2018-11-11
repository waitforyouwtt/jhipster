package com.book.repository;

import com.book.domain.LogUserInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LogUserInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LogUserInfoRepository extends JpaRepository<LogUserInfo, Long> {

}
