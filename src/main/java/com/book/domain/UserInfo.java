package com.book.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.book.domain.enumeration.Sex;

import com.book.domain.enumeration.Ascription;

/**
 * 《遮天》人员概况表
 */
@ApiModel(description = "《遮天》人员概况表")
@Entity
@Table(name = "user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    @Column(name = "user_id")
    private String userId;

    /**
     * 人物名字
     */
    @ApiModelProperty(value = "人物名字")
    @Column(name = "user_name")
    private String userName;

    /**
     * 人物昵称
     */
    @ApiModelProperty(value = "人物昵称")
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Sex sex;

    /**
     * 归属门派
     */
    @ApiModelProperty(value = "归属门派")
    @Enumerated(EnumType.STRING)
    @Column(name = "ascription")
    private Ascription ascription;

    /**
     * 家庭住址
     */
    @ApiModelProperty(value = "家庭住址")
    @Column(name = "address")
    private String address;

    /**
     * 电子邮箱
     */
    @ApiModelProperty(value = "电子邮箱")
    @Column(name = "email")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    @Column(name = "birthday")
    private Instant birthday;

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

    public String getUserId() {
        return userId;
    }

    public UserInfo userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public UserInfo userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public UserInfo nickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Sex getSex() {
        return sex;
    }

    public UserInfo sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Ascription getAscription() {
        return ascription;
    }

    public UserInfo ascription(Ascription ascription) {
        this.ascription = ascription;
        return this;
    }

    public void setAscription(Ascription ascription) {
        this.ascription = ascription;
    }

    public String getAddress() {
        return address;
    }

    public UserInfo address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public UserInfo email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserInfo phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getBirthday() {
        return birthday;
    }

    public UserInfo birthday(Instant birthday) {
        this.birthday = birthday;
        return this;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public UserInfo createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public UserInfo updateTime(Instant updateTime) {
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
        UserInfo userInfo = (UserInfo) o;
        if (userInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserInfo{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", userName='" + getUserName() + "'" +
            ", nickName='" + getNickName() + "'" +
            ", sex='" + getSex() + "'" +
            ", ascription='" + getAscription() + "'" +
            ", address='" + getAddress() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", birthday='" + getBirthday() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
