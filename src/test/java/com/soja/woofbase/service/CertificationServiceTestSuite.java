package com.soja.woofbase.service;

import com.soja.woofbase.repository.CertificationRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CertificationServiceTestSuite {

    @Autowired
    private CertificationRepository certificationRepository;
}
