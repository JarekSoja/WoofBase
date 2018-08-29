package com.soja.woofbase.service.schedulerServices;

import com.soja.woofbase.domain.Mail;
import com.soja.woofbase.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final MailCreatorService mailCreatorService;

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(MailCreatorService mailCreatorService, JavaMailSender javaMailSender) {
        this.mailCreatorService = mailCreatorService;
        this.javaMailSender = javaMailSender;
    }

    public void send(final Mail mail, final User user) {
        try {
            javaMailSender.send(createMimeMessage(mail, user));
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail, final User user) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(user.getEmailAddress());
            messageHelper.setSubject(mail.getMailSubject());
            messageHelper.setText(mailCreatorService.buildAlertEmail(mail.getMailBody()), true);
        };
    }


}
