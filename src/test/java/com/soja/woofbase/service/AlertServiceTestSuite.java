package com.soja.woofbase.service;

import com.soja.woofbase.domain.Alert;
import com.soja.woofbase.domain.Mail;
import com.soja.woofbase.domain.User;
import com.soja.woofbase.repository.AlertRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@Runwith(SpringRunner.class)
@SpringBootTest
public class AlertServiceTestSuite {

    @Autowired
    private AlertRepository alertRepository;

    @Test
    public void testGetAllAlerts() {
        //Given
        alertRepository = mock(AlertRepository.class);
        Alert alert1 = new Alert(
                LocalDate.of(2018, 2, 11),
                LocalDate.of(2018, 3, 22),
                true,
                true,
                new User(),
                new Mail());
        List<Alert> alerts = new ArrayList<>();

        //When
        when(alertRepository.save(any(Alert.class))).thenReturn(alert1);
        alerts.add(alert1);

        //Then
        Assert.assertEquals(1, alerts.size());
    }
}
