package com.book.repository;

import com.book.domain.UserInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UserInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    /**
     * 根据userId 查询一条记录
     */
    UserInfo findUserInfoByUserId(String userId);

}
