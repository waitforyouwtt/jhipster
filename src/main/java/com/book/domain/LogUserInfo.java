package com.book.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A LogUserInfo.
 */
@Entity
@Table(name = "log_user_info")
public class LogUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The firstname attribute.
     */
    @ApiModelProperty(value = "The firstname attribute.")
    @Column(name = "user_name")
    private String userName;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "hire_date")
    private Instant hireDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public LogUserInfo userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public LogUserInfo nickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public LogUserInfo email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LogUserInfo phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getHireDate() {
        return hireDate;
    }

    public LogUserInfo hireDate(Instant hireDate) {
        this.hireDate = hireDate;
        return this;
    }

    public void setHireDate(Instant hireDate) {
        this.hireDate = hireDate;
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
        LogUserInfo logUserInfo = (LogUserInfo) o;
        if (logUserInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), logUserInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LogUserInfo{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            ", nickName='" + getNickName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", hireDate='" + getHireDate() + "'" +
            "}";
    }
}
