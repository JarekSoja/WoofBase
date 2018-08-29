package com.soja.woofbase.service.schedulerServices;

import com.soja.woofbase.domain.Alert;
import com.soja.woofbase.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SchedulerService {

    private final AlertService alertService;
    private final EmailService emailService;

    @Autowired
    public SchedulerService(AlertService alertService, EmailService emailService) {
        this.alertService = alertService;
        this.emailService = emailService;
    }

    @Async("threadPoolTaskExecutor")
    @Scheduled(cron = "0 0 12 1/1 * ? *")
    public void asyncCheckAlerts() {
        Iterable<Alert> alerts = alertService.getAllAlerts();
        for (Alert a : alerts) {
            if (shouldAlertActivate(a)) {
                sendAlerts(a);
            }
        }
    }


    private boolean shouldAlertActivate(Alert alert) {
        return LocalDate.now().equals(alert.getAlertDate());
    }

    private void sendAlerts(Alert alert) {
        if (alert.isMailAlert()) {
            emailService.send(alert.getMail(), alert.getUser());
        }
        if (alert.isTextAlert()) {
            //TODO call to text service sending alerts
        }
    }

}
