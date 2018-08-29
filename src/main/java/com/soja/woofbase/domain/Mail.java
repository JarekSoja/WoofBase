package com.soja.woofbase.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MAILS")
public class Mail {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "MAIL_ID", unique = true)
    private Long mailId;

    @Column(name = "MAIL_SUBJECT")
    private String mailSubject;

    @Column(name = "MAIL_BODY")
    private String mailBody;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ALERT_MAIL")
    private Alert alert;


    public Mail(String mailSubject, String mailBody, Alert alert) {
        this.mailSubject = mailSubject;
        this.mailBody = mailBody;
        this.alert = alert;
    }

    public Mail() {
    }

    public Long getMailId() {
        return mailId;
    }

    public void setMailId(Long mailId) {
        this.mailId = mailId;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailBody() {
        return mailBody;
    }

    public void setMailBody(String mailBody) {
        this.mailBody = mailBody;
    }

    //TODO figure a way to connect particular email row in database with alert genus
}
