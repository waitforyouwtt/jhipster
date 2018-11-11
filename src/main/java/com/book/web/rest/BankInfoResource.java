package com.book.web.rest;

import com.book.RepeatSubmission.CheckToken;
import com.book.RepeatSubmission.ConstantUtils;
import com.codahale.metrics.annotation.Timed;
import com.book.domain.BankInfo;
import com.book.service.BankInfoService;
import com.book.web.rest.errors.BadRequestAlertException;
import com.book.web.rest.util.HeaderUtil;
import com.book.web.rest.util.PaginationUtil;
import com.google.common.collect.ImmutableList;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing BankInfo.
 */
@RestController
@RequestMapping("/api")
public class BankInfoResource {

    private final Logger log = LoggerFactory.getLogger(BankInfoResource.class);

    private static final String ENTITY_NAME = "bankInfo";

    private final BankInfoService bankInfoService;

    public BankInfoResource(BankInfoService bankInfoService) {
        this.bankInfoService = bankInfoService;
    }

    @PostMapping("/bank-infos")
    @Timed
    public ResponseEntity<BankInfo> createBankInfo(@RequestBody BankInfo bankInfo) throws URISyntaxException {
        log.debug("REST request to save BankInfo : {}", bankInfo);
        if (bankInfo.getId() != null) {
            throw new BadRequestAlertException("A new bankInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BankInfo result = bankInfoService.save(bankInfo);
        return ResponseEntity.created(new URI("/api/bank-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    @PutMapping("/bank-infos")
    @Timed
    public ResponseEntity<BankInfo> updateBankInfo(@RequestBody BankInfo bankInfo) throws URISyntaxException {
        log.debug("REST request to update BankInfo : {}", bankInfo);
        if (bankInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BankInfo result = bankInfoService.save(bankInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bankInfo.getId().toString()))
            .body(result);
    }


    @GetMapping("/bank-infos")
    @Timed
    public ResponseEntity<List<BankInfo>> getAllBankInfos(Pageable pageable) {
        log.debug("REST request to get a page of BankInfos");
        Page<BankInfo> page = bankInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/bank-infos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    @GetMapping("/bank-infos/{id}")
    @Timed
    public ResponseEntity<BankInfo> getBankInfo(@PathVariable Long id) {
        log.debug("REST request to get BankInfo : {}", id);
        Optional<BankInfo> bankInfo = bankInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bankInfo);
    }

    @DeleteMapping("/bank-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteBankInfo(@PathVariable Long id) {
        log.debug("REST request to delete BankInfo : {}", id);
        bankInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @ApiOperation(value = "保存银行卡信息",nickname = "保存银行卡信息")
    @PostMapping(value = "bank-info")
    public ResponseEntity<BankInfo> saveBankInfo(@RequestBody BankInfo bankInfo) throws URISyntaxException {
        BankInfo result = bankInfoService.save(bankInfo);
        return ResponseEntity.created(new URI("/api/bank-info/")).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME,result.getId().toString())).body(result);
    }
    @ApiOperation(value = "根据bankId获取一个对象")
    @GetMapping(value = "/bank-infos/{bankId}")
    public ResponseEntity<BankInfo> getOneBankInfo(@RequestParam String bankId){
        Optional<BankInfo> bankInfo = bankInfoService.findBankInfoByBankId(bankId);
        return ResponseUtil.wrapOrNotFound(bankInfo);
    }
    @ApiOperation(value = "获取银行集合列表")
    @GetMapping(value = "/find-bank-infos")
    public List<BankInfo> findList(){
        List<BankInfo> list = bankInfoService.findAll();
        if(!list.isEmpty()){
            throw new BadRequestAlertException("已经获取到集合了","","200");
        }
        return list;
    }


}
