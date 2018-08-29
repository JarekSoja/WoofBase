package com.soja.woofbase.domain.dto.outbound;

import com.soja.woofbase.domain.Alert;
import com.soja.woofbase.domain.DogOwned;

import java.util.List;

public class UserDtoOut {

    private String emailAddress;
    private String mobileNumber;
    private List<DogOwned> dogsOwned;
    private List<Alert> alerts;

    public UserDtoOut(String emailAddress, String mobileNumber, List<DogOwned> dogsOwned, List<Alert> alerts) {
        this.emailAddress = emailAddress;
        this.mobileNumber = mobileNumber;
        this.dogsOwned = dogsOwned;
        this.alerts = alerts;
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
