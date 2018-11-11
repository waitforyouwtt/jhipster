package com.book.web.rest;

import com.book.PutongApp;

import com.book.domain.BankInfo;
import com.book.repository.BankInfoRepository;
import com.book.service.BankInfoService;
import com.book.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.book.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BankInfoResource REST controller.
 *
 * @see BankInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PutongApp.class)
public class BankInfoResourceIntTest {

    private static final String DEFAULT_BANK_ID = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ID = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BANK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_BRANCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BANK_BRANCH_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private BankInfoRepository bankInfoRepository;
    
    @Autowired
    private BankInfoService bankInfoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBankInfoMockMvc;

    private BankInfo bankInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BankInfoResource bankInfoResource = new BankInfoResource(bankInfoService);
        this.restBankInfoMockMvc = MockMvcBuilders.standaloneSetup(bankInfoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BankInfo createEntity(EntityManager em) {
        BankInfo bankInfo = new BankInfo()
            .bankId(DEFAULT_BANK_ID)
            .userId(DEFAULT_USER_ID)
            .account(DEFAULT_ACCOUNT)
            .accountName(DEFAULT_ACCOUNT_NAME)
            .bankName(DEFAULT_BANK_NAME)
            .bankBranchName(DEFAULT_BANK_BRANCH_NAME)
            .amount(DEFAULT_AMOUNT)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return bankInfo;
    }

    @Before
    public void initTest() {
        bankInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createBankInfo() throws Exception {
        int databaseSizeBeforeCreate = bankInfoRepository.findAll().size();

        // Create the BankInfo
        restBankInfoMockMvc.perform(post("/api/bank-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankInfo)))
            .andExpect(status().isCreated());

        // Validate the BankInfo in the database
        List<BankInfo> bankInfoList = bankInfoRepository.findAll();
        assertThat(bankInfoList).hasSize(databaseSizeBeforeCreate + 1);
        BankInfo testBankInfo = bankInfoList.get(bankInfoList.size() - 1);
        assertThat(testBankInfo.getBankId()).isEqualTo(DEFAULT_BANK_ID);
        assertThat(testBankInfo.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testBankInfo.getAccount()).isEqualTo(DEFAULT_ACCOUNT);
        assertThat(testBankInfo.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testBankInfo.getBankName()).isEqualTo(DEFAULT_BANK_NAME);
        assertThat(testBankInfo.getBankBranchName()).isEqualTo(DEFAULT_BANK_BRANCH_NAME);
        assertThat(testBankInfo.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testBankInfo.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testBankInfo.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createBankInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bankInfoRepository.findAll().size();

        // Create the BankInfo with an existing ID
        bankInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBankInfoMockMvc.perform(post("/api/bank-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankInfo)))
            .andExpect(status().isBadRequest());

        // Validate the BankInfo in the database
        List<BankInfo> bankInfoList = bankInfoRepository.findAll();
        assertThat(bankInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBankInfos() throws Exception {
        // Initialize the database
        bankInfoRepository.saveAndFlush(bankInfo);

        // Get all the bankInfoList
        restBankInfoMockMvc.perform(get("/api/bank-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bankInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].bankId").value(hasItem(DEFAULT_BANK_ID.toString())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].account").value(hasItem(DEFAULT_ACCOUNT.toString())))
            .andExpect(jsonPath("$.[*].accountName").value(hasItem(DEFAULT_ACCOUNT_NAME.toString())))
            .andExpect(jsonPath("$.[*].bankName").value(hasItem(DEFAULT_BANK_NAME.toString())))
            .andExpect(jsonPath("$.[*].bankBranchName").value(hasItem(DEFAULT_BANK_BRANCH_NAME.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getBankInfo() throws Exception {
        // Initialize the database
        bankInfoRepository.saveAndFlush(bankInfo);

        // Get the bankInfo
        restBankInfoMockMvc.perform(get("/api/bank-infos/{id}", bankInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bankInfo.getId().intValue()))
            .andExpect(jsonPath("$.bankId").value(DEFAULT_BANK_ID.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.account").value(DEFAULT_ACCOUNT.toString()))
            .andExpect(jsonPath("$.accountName").value(DEFAULT_ACCOUNT_NAME.toString()))
            .andExpect(jsonPath("$.bankName").value(DEFAULT_BANK_NAME.toString()))
            .andExpect(jsonPath("$.bankBranchName").value(DEFAULT_BANK_BRANCH_NAME.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBankInfo() throws Exception {
        // Get the bankInfo
        restBankInfoMockMvc.perform(get("/api/bank-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBankInfo() throws Exception {
        // Initialize the database
        bankInfoService.save(bankInfo);

        int databaseSizeBeforeUpdate = bankInfoRepository.findAll().size();

        // Update the bankInfo
        BankInfo updatedBankInfo = bankInfoRepository.findById(bankInfo.getId()).get();
        // Disconnect from session so that the updates on updatedBankInfo are not directly saved in db
        em.detach(updatedBankInfo);
        updatedBankInfo
            .bankId(UPDATED_BANK_ID)
            .userId(UPDATED_USER_ID)
            .account(UPDATED_ACCOUNT)
            .accountName(UPDATED_ACCOUNT_NAME)
            .bankName(UPDATED_BANK_NAME)
            .bankBranchName(UPDATED_BANK_BRANCH_NAME)
            .amount(UPDATED_AMOUNT)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restBankInfoMockMvc.perform(put("/api/bank-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBankInfo)))
            .andExpect(status().isOk());

        // Validate the BankInfo in the database
        List<BankInfo> bankInfoList = bankInfoRepository.findAll();
        assertThat(bankInfoList).hasSize(databaseSizeBeforeUpdate);
        BankInfo testBankInfo = bankInfoList.get(bankInfoList.size() - 1);
        assertThat(testBankInfo.getBankId()).isEqualTo(UPDATED_BANK_ID);
        assertThat(testBankInfo.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testBankInfo.getAccount()).isEqualTo(UPDATED_ACCOUNT);
        assertThat(testBankInfo.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testBankInfo.getBankName()).isEqualTo(UPDATED_BANK_NAME);
        assertThat(testBankInfo.getBankBranchName()).isEqualTo(UPDATED_BANK_BRANCH_NAME);
        assertThat(testBankInfo.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testBankInfo.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testBankInfo.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingBankInfo() throws Exception {
        int databaseSizeBeforeUpdate = bankInfoRepository.findAll().size();

        // Create the BankInfo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBankInfoMockMvc.perform(put("/api/bank-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankInfo)))
            .andExpect(status().isBadRequest());

        // Validate the BankInfo in the database
        List<BankInfo> bankInfoList = bankInfoRepository.findAll();
        assertThat(bankInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBankInfo() throws Exception {
        // Initialize the database
        bankInfoService.save(bankInfo);

        int databaseSizeBeforeDelete = bankInfoRepository.findAll().size();

        // Get the bankInfo
        restBankInfoMockMvc.perform(delete("/api/bank-infos/{id}", bankInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BankInfo> bankInfoList = bankInfoRepository.findAll();
        assertThat(bankInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BankInfo.class);
        BankInfo bankInfo1 = new BankInfo();
        bankInfo1.setId(1L);
        BankInfo bankInfo2 = new BankInfo();
        bankInfo2.setId(bankInfo1.getId());
        assertThat(bankInfo1).isEqualTo(bankInfo2);
        bankInfo2.setId(2L);
        assertThat(bankInfo1).isNotEqualTo(bankInfo2);
        bankInfo1.setId(null);
        assertThat(bankInfo1).isNotEqualTo(bankInfo2);
    }
}
