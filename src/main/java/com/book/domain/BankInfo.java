package com.book.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * 银行表
 */
@ApiModel(description = "银行表")
@Entity
@Table(name = "bank_info")
public class BankInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 记录Id
     */
    @ApiModelProperty(value = "记录Id")
    @Column(name = "bank_id")
    private String bankId;

    /**
     * 用户Id
     */
    @ApiModelProperty(value = "用户Id")
    @Column(name = "user_id")
    private String userId;

    /**
     * 开户账号
     */
    @ApiModelProperty(value = "开户账号")
    @Column(name = "jhi_account")
    private String account;

    /**
     * 开户名
     */
    @ApiModelProperty(value = "开户名")
    @Column(name = "account_name")
    private String accountName;

    /**
     * 开户银行
     */
    @ApiModelProperty(value = "开户银行")
    @Column(name = "bank_name")
    private String bankName;

    /**
     * 银行支行
     */
    @ApiModelProperty(value = "银行支行")
    @Column(name = "bank_branch_name")
    private String bankBranchName;

    /**
     * 存款金额
     */
    @ApiModelProperty(value = "存款金额")
    @Column(name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Instant createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @Column(name = "update_time")
    private Instant updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankId() {
        return bankId;
    }

    public BankInfo bankId(String bankId) {
        this.bankId = bankId;
        return this;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getUserId() {
        return userId;
    }

    public BankInfo userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public BankInfo account(String account) {
        this.account = account;
        return this;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountName() {
        return accountName;
    }

    public BankInfo accountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankName() {
        return bankName;
    }

    public BankInfo bankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public BankInfo bankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
        return this;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BankInfo amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public BankInfo createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public BankInfo updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BankInfo bankInfo = (BankInfo) o;
        if (bankInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bankInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BankInfo{" +
            "id=" + getId() +
            ", bankId='" + getBankId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", account='" + getAccount() + "'" +
            ", accountName='" + getAccountName() + "'" +
            ", bankName='" + getBankName() + "'" +
            ", bankBranchName='" + getBankBranchName() + "'" +
            ", amount=" + getAmount() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
