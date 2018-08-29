package com.soja.woofbase.service.schedulerServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    private final TemplateEngine templateEngine;

    @Autowired
    public MailCreatorService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String buildAlertEmail(String message) {
        Context context = new Context();
        return templateEngine.process("mail/info-mail", context);
        //TODO implement Thymeleaf templates

    }


}
