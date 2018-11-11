package com.book.web.rest;

import com.book.PutongApp;

import com.book.domain.LogUserInfo;
import com.book.repository.LogUserInfoRepository;
import com.book.service.LogUserInfoService;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.book.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LogUserInfoResource REST controller.
 *
 * @see LogUserInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PutongApp.class)
public class LogUserInfoResourceIntTest {

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NICK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NICK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final Instant DEFAULT_HIRE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_HIRE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private LogUserInfoRepository logUserInfoRepository;
    
    @Autowired
    private LogUserInfoService logUserInfoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLogUserInfoMockMvc;

    private LogUserInfo logUserInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LogUserInfoResource logUserInfoResource = new LogUserInfoResource(logUserInfoService);
        this.restLogUserInfoMockMvc = MockMvcBuilders.standaloneSetup(logUserInfoResource)
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
    public static LogUserInfo createEntity(EntityManager em) {
        LogUserInfo logUserInfo = new LogUserInfo()
            .userName(DEFAULT_USER_NAME)
            .nickName(DEFAULT_NICK_NAME)
            .email(DEFAULT_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .hireDate(DEFAULT_HIRE_DATE);
        return logUserInfo;
    }

    @Before
    public void initTest() {
        logUserInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createLogUserInfo() throws Exception {
        int databaseSizeBeforeCreate = logUserInfoRepository.findAll().size();

        // Create the LogUserInfo
        restLogUserInfoMockMvc.perform(post("/api/log-user-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logUserInfo)))
            .andExpect(status().isCreated());

        // Validate the LogUserInfo in the database
        List<LogUserInfo> logUserInfoList = logUserInfoRepository.findAll();
        assertThat(logUserInfoList).hasSize(databaseSizeBeforeCreate + 1);
        LogUserInfo testLogUserInfo = logUserInfoList.get(logUserInfoList.size() - 1);
        assertThat(testLogUserInfo.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testLogUserInfo.getNickName()).isEqualTo(DEFAULT_NICK_NAME);
        assertThat(testLogUserInfo.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testLogUserInfo.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testLogUserInfo.getHireDate()).isEqualTo(DEFAULT_HIRE_DATE);
    }

    @Test
    @Transactional
    public void createLogUserInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = logUserInfoRepository.findAll().size();

        // Create the LogUserInfo with an existing ID
        logUserInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLogUserInfoMockMvc.perform(post("/api/log-user-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logUserInfo)))
            .andExpect(status().isBadRequest());

        // Validate the LogUserInfo in the database
        List<LogUserInfo> logUserInfoList = logUserInfoRepository.findAll();
        assertThat(logUserInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLogUserInfos() throws Exception {
        // Initialize the database
        logUserInfoRepository.saveAndFlush(logUserInfo);

        // Get all the logUserInfoList
        restLogUserInfoMockMvc.perform(get("/api/log-user-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(logUserInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].nickName").value(hasItem(DEFAULT_NICK_NAME.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].hireDate").value(hasItem(DEFAULT_HIRE_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getLogUserInfo() throws Exception {
        // Initialize the database
        logUserInfoRepository.saveAndFlush(logUserInfo);

        // Get the logUserInfo
        restLogUserInfoMockMvc.perform(get("/api/log-user-infos/{id}", logUserInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(logUserInfo.getId().intValue()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.nickName").value(DEFAULT_NICK_NAME.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.hireDate").value(DEFAULT_HIRE_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLogUserInfo() throws Exception {
        // Get the logUserInfo
        restLogUserInfoMockMvc.perform(get("/api/log-user-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLogUserInfo() throws Exception {
        // Initialize the database
        logUserInfoService.save(logUserInfo);

        int databaseSizeBeforeUpdate = logUserInfoRepository.findAll().size();

        // Update the logUserInfo
        LogUserInfo updatedLogUserInfo = logUserInfoRepository.findById(logUserInfo.getId()).get();
        // Disconnect from session so that the updates on updatedLogUserInfo are not directly saved in db
        em.detach(updatedLogUserInfo);
        updatedLogUserInfo
            .userName(UPDATED_USER_NAME)
            .nickName(UPDATED_NICK_NAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .hireDate(UPDATED_HIRE_DATE);

        restLogUserInfoMockMvc.perform(put("/api/log-user-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLogUserInfo)))
            .andExpect(status().isOk());

        // Validate the LogUserInfo in the database
        List<LogUserInfo> logUserInfoList = logUserInfoRepository.findAll();
        assertThat(logUserInfoList).hasSize(databaseSizeBeforeUpdate);
        LogUserInfo testLogUserInfo = logUserInfoList.get(logUserInfoList.size() - 1);
        assertThat(testLogUserInfo.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testLogUserInfo.getNickName()).isEqualTo(UPDATED_NICK_NAME);
        assertThat(testLogUserInfo.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testLogUserInfo.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testLogUserInfo.getHireDate()).isEqualTo(UPDATED_HIRE_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingLogUserInfo() throws Exception {
        int databaseSizeBeforeUpdate = logUserInfoRepository.findAll().size();

        // Create the LogUserInfo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLogUserInfoMockMvc.perform(put("/api/log-user-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logUserInfo)))
            .andExpect(status().isBadRequest());

        // Validate the LogUserInfo in the database
        List<LogUserInfo> logUserInfoList = logUserInfoRepository.findAll();
        assertThat(logUserInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLogUserInfo() throws Exception {
        // Initialize the database
        logUserInfoService.save(logUserInfo);

        int databaseSizeBeforeDelete = logUserInfoRepository.findAll().size();

        // Get the logUserInfo
        restLogUserInfoMockMvc.perform(delete("/api/log-user-infos/{id}", logUserInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LogUserInfo> logUserInfoList = logUserInfoRepository.findAll();
        assertThat(logUserInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LogUserInfo.class);
        LogUserInfo logUserInfo1 = new LogUserInfo();
        logUserInfo1.setId(1L);
        LogUserInfo logUserInfo2 = new LogUserInfo();
        logUserInfo2.setId(logUserInfo1.getId());
        assertThat(logUserInfo1).isEqualTo(logUserInfo2);
        logUserInfo2.setId(2L);
        assertThat(logUserInfo1).isNotEqualTo(logUserInfo2);
        logUserInfo1.setId(null);
        assertThat(logUserInfo1).isNotEqualTo(logUserInfo2);
    }
}
