package com.soja.woofbase.domain.dto.outbound;

import com.soja.woofbase.domain.Mail;
import com.soja.woofbase.domain.User;

import java.time.LocalDate;

public class AlertDtoOut {

    private LocalDate alertDate;
    private LocalDate reminderDate;
    private boolean isTextAlert;
    private boolean isMailAlert;
    private User user;
    private Mail mail;

    public AlertDtoOut(LocalDate alertDate, LocalDate reminderDate, boolean isTextAlert, boolean isMailAlert, User user, Mail mail) {
        this.alertDate = alertDate;
        this.reminderDate = reminderDate;
        this.isTextAlert = isTextAlert;
        this.isMailAlert = isMailAlert;
        this.user = user;
        this.mail = mail;
    }

    public void setAlertDate(LocalDate alertDate) {
        this.alertDate = alertDate;
    }

    public void setReminderDate(LocalDate reminderDate) {
        this.reminderDate = reminderDate;
    }

    public void setTextAlert(boolean textAlert) {
        isTextAlert = textAlert;
    }

    public void setMailAlert(boolean mailAlert) {
        isMailAlert = mailAlert;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }
}

