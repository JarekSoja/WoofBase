package com.soja.woofbase.domain.dto.inbound;

import com.soja.woofbase.domain.Mail;
import com.soja.woofbase.domain.User;

import java.time.LocalDate;

public class AlertDtoIn {


    private LocalDate alertDate;
    private LocalDate reminderDate;
    private boolean isTextAlert;
    private boolean isMailAlert;
    private User user;
    private Mail mail;

    public AlertDtoIn(LocalDate alertDate, LocalDate reminderDate, boolean isTextAlert, boolean isMailAlert, User user, Mail mail) {
        this.alertDate = alertDate;
        this.reminderDate = reminderDate;
        this.isTextAlert = isTextAlert;
        this.isMailAlert = isMailAlert;
        this.user = user;
        this.mail = mail;
    }

    public LocalDate getAlertDate() {
        return alertDate;
    }

    public LocalDate getReminderDate() {
        return reminderDate;
    }

    public boolean isTextAlert() {
        return isTextAlert;
    }

    public boolean isMailAlert() {
        return isMailAlert;
    }

    public User getUser() {
        return user;
    }

    public Mail getMail() {
        return mail;
    }
}
