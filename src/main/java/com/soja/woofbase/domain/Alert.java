package com.soja.woofbase.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "ALERTS")
public class Alert {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ALERT_ID", unique = true)
    private long alertId;

    @Column(name = "ALERT_DATES")
    private LocalDate alertDate;

    @Column(name = "REMINDER_DATES")
    private LocalDate reminderDate;

    @Column(name = "IS_TEXT_ALERT_SET")
    private boolean isTextAlert;

    @Column(name = "IS_EMAIL_ALERT_SET")
    private boolean isMailAlert;

    @ManyToOne
    @JoinColumn(name = "USER_ALERTS")
    private User user;

    @OneToOne(
            mappedBy = "alert",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            optional = false
    )
    private Mail mail;

    public Alert(LocalDate alertDate, LocalDate reminderDate, boolean isTextAlert, boolean isMailAlert, User user, Mail mail) {
        this.alertDate = alertDate;
        this.reminderDate = reminderDate;
        this.isTextAlert = isTextAlert;
        this.isMailAlert = isMailAlert;
        this.user = user;
        this.mail = mail;
    }

    public long getAlertId() {
        return alertId;
    }

    public void setAlertId(long alertId) {
        this.alertId = alertId;
    }

    public LocalDate getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(LocalDate alertDate) {
        this.alertDate = alertDate;
    }

    public LocalDate getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(LocalDate reminderDate) {
        this.reminderDate = reminderDate;
    }

    public boolean isTextAlert() {
        return isTextAlert;
    }

    public void setTextAlert(boolean textAlert) {
        isTextAlert = textAlert;
    }

    public boolean isMailAlert() {
        return isMailAlert;
    }

    public void setMailAlert(boolean mailAlert) {
        isMailAlert = mailAlert;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }
}
