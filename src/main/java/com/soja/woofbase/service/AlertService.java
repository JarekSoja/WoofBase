package com.soja.woofbase.service;

import com.soja.woofbase.domain.Alert;
import com.soja.woofbase.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    private final AlertRepository alertRepository;

    @Autowired
    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public Iterable<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }
}
