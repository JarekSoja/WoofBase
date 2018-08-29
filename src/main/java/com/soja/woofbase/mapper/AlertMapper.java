package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.Alert;
import com.soja.woofbase.domain.dto.inbound.AlertDtoIn;
import com.soja.woofbase.domain.dto.outbound.AlertDtoOut;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlertMapper {

    public Alert mapToAlert(AlertDtoIn alertDtoIn) {
        return new Alert(
                alertDtoIn.getAlertDate(),
                alertDtoIn.getReminderDate(),
                alertDtoIn.isTextAlert(),
                alertDtoIn.isMailAlert(),
                alertDtoIn.getUser(),
                alertDtoIn.getMail()
        );
    }

    public AlertDtoOut mapToAlertDtoOut(Alert alert) {
        return new AlertDtoOut(
                alert.getAlertDate(),
                alert.getReminderDate(),
                alert.isTextAlert(),
                alert.isMailAlert(),
                alert.getUser(),
                alert.getMail()
        );
    }

    public List<AlertDtoOut> mapToAlertDtoOutList(List<Alert> alerts) {
        return alerts.stream()
                .map(a -> mapToAlertDtoOut(a))
                .collect(Collectors.toList());
    }
}
