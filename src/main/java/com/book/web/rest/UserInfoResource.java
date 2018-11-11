package com.book.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.book.domain.UserInfo;
import com.book.service.UserInfoService;
import com.book.web.rest.errors.BadRequestAlertException;
import com.book.web.rest.util.HeaderUtil;
import com.book.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UserInfo.
 */
@RestController
@RequestMapping("/api")
public class UserInfoResource {

    private final Logger log = LoggerFactory.getLogger(UserInfoResource.class);

    private static final String ENTITY_NAME = "userInfo";

    private final UserInfoService userInfoService;

    public UserInfoResource(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping("/user-infos")
    @Timed
    public ResponseEntity<UserInfo> createUserInfo(@RequestBody UserInfo userInfo) throws URISyntaxException {
        log.debug("REST request to save UserInfo : {}", userInfo);
        if (userInfo.getId() != null) {
            throw new BadRequestAlertException("A new userInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserInfo result = userInfoService.save(userInfo);
        return ResponseEntity.created(new URI("/api/user-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/user-infos")
    @Timed
    public ResponseEntity<UserInfo> updateUserInfo(@RequestBody UserInfo userInfo) throws URISyntaxException {
        log.debug("REST request to update UserInfo : {}", userInfo);
        if (userInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserInfo result = userInfoService.save(userInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userInfo.getId().toString()))
            .body(result);
    }

    @GetMapping("/user-infos")
    @Timed
    public ResponseEntity<List<UserInfo>> getAllUserInfos(Pageable pageable) {
        log.debug("REST request to get a page of UserInfos");
        Page<UserInfo> page = userInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-infos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/user-infos/{id}")
    @Timed
    public ResponseEntity<UserInfo> getUserInfo(@PathVariable Long id) {
        log.debug("REST request to get UserInfo : {}", id);
        Optional<UserInfo> userInfo = userInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userInfo);
    }

    @DeleteMapping("/user-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserInfo(@PathVariable Long id) {
        log.debug("REST request to delete UserInfo : {}", id);
        userInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
