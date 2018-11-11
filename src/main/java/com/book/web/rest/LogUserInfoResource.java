package com.book.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.book.domain.LogUserInfo;
import com.book.service.LogUserInfoService;
import com.book.web.rest.errors.BadRequestAlertException;
import com.book.web.rest.util.HeaderUtil;
import com.book.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
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
 * REST controller for managing LogUserInfo.
 */
@RestController
@RequestMapping("/api")
public class LogUserInfoResource {

    private final Logger log = LoggerFactory.getLogger(LogUserInfoResource.class);

    private static final String ENTITY_NAME = "logUserInfo";

    private final LogUserInfoService logUserInfoService;

    public LogUserInfoResource(LogUserInfoService logUserInfoService) {
        this.logUserInfoService = logUserInfoService;
    }


    @PostMapping("/log-user-infos")
    @Timed
    public ResponseEntity<LogUserInfo> createLogUserInfo(@RequestBody LogUserInfo logUserInfo) throws URISyntaxException {
        log.debug("REST request to save LogUserInfo : {}", logUserInfo);
        if (logUserInfo.getId() != null) {
            throw new BadRequestAlertException("A new logUserInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LogUserInfo result = logUserInfoService.save(logUserInfo);
        return ResponseEntity.created(new URI("/api/log-user-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    @PutMapping("/log-user-infos")
    @Timed
    public ResponseEntity<LogUserInfo> updateLogUserInfo(@RequestBody LogUserInfo logUserInfo) throws URISyntaxException {
        log.debug("REST request to update LogUserInfo : {}", logUserInfo);
        if (logUserInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LogUserInfo result = logUserInfoService.save(logUserInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, logUserInfo.getId().toString()))
            .body(result);
    }


    @GetMapping("/log-user-infos")
    @Timed
    public ResponseEntity<List<LogUserInfo>> getAllLogUserInfos(Pageable pageable) {
        log.debug("REST request to get a page of LogUserInfos");
        Page<LogUserInfo> page = logUserInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/log-user-infos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/log-user-infos/{id}")
    @Timed
    public ResponseEntity<LogUserInfo> getLogUserInfo(@PathVariable Long id) {
        log.debug("REST request to get LogUserInfo : {}", id);
        Optional<LogUserInfo> logUserInfo = logUserInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(logUserInfo);
    }


    @DeleteMapping("/log-user-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteLogUserInfo(@PathVariable Long id) {
        log.debug("REST request to delete LogUserInfo : {}", id);
        logUserInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @ApiOperation(value = "创建用户")
    @PostMapping("/create-userinfo")
    public ResponseEntity<LogUserInfo> createUserInfo (@RequestBody LogUserInfo logUserInfo) throws URISyntaxException {
        log.info("---------------");
       LogUserInfo result =  logUserInfoService.save(logUserInfo);
       return ResponseEntity.created(new URI("/api/create-userinfo/" + result.getId()))
           .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
           .body(result);
    }
}
