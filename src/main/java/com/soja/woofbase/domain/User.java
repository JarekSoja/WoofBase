package com.soja.woofbase.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "USER_ID", unique = true)
    private long userId;

    @Column(name = "EMAIL_ADRESS")
    @Email (message = "Please provide valid email address")
    private String emailAddress;

    @Column(name = "PHONE_NUMBER")
    @Length(min = 9, max = 9, message = "Please provide valid mobile number")
    private String mobileNumber;

    @OneToMany(
            targetEntity = DogOwned.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<DogOwned> dogsOwned;

    @OneToMany(
            targetEntity = Alert.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Alert> alerts;

    public User(String emailAddress, String mobileNumber, List<DogOwned> dogsOwned, List<Alert> alerts) {
        this.emailAddress = emailAddress;
        this.mobileNumber = mobileNumber;
        this.dogsOwned = dogsOwned;
        this.alerts = alerts;
    }

    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public List<DogOwned> getDogsOwned() {
        return dogsOwned;
    }

    public void setDogsOwned(List<DogOwned> dogsOwned) {
        this.dogsOwned = dogsOwned;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

}
