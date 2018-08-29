package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.Certification;
import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.User;
import com.soja.woofbase.domain.dto.inbound.CertificationDtoIn;
import com.soja.woofbase.domain.dto.outbound.CertificationDtoOut;
import com.soja.woofbase.repository.DogOwnedRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CertificationMapperTestSuite {

    @Autowired
    private CertificationMapper certificationMapper;

    @Autowired
    private DogOwnedRepository dogOwnedRepository;

    @After
    public void clearData() {
        dogOwnedRepository.deleteAll();
    }


    @Test
    public void testMapToCertification() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3,11));
        dogOwnedRepository.save(fafik);
        CertificationDtoIn certificationDtoIn = new CertificationDtoIn(fafik, "Czampion gryzienia mebli", "www.test.com");

        //When
        Certification testedCertification = certificationMapper.mapToCertification(certificationDtoIn);
        String testedURL = "www.test.com";
        String testedTitle = "Czampion gryzienia mebli";

        //Then
        Assert.assertEquals(testedURL, testedCertification.getCertificationURL());
        Assert.assertEquals(testedTitle, testedCertification.getCertificationName());
    }

    @Test
    public void testMapToCertificationDtoOut() {
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3,11));
        dogOwnedRepository.save(fafik);
        Certification certification = new Certification(fafik, "Czampion gryzienia mebli", "www.test.com");

        //When
        CertificationDtoOut testedCertification = certificationMapper.mapToCertificationDtoOut(certification);
        String testedURL = "www.test.com";
        String testedTitle = "Czampion gryzienia mebli";

        //Then
        Assert.assertEquals(testedURL, testedCertification.getCertificationDtoOutURL());
        Assert.assertEquals(testedTitle, testedCertification.getCertificationDtoOutName());
    }

    @Test
    public void testMapToCertificationDtoOutList() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3,11));
        dogOwnedRepository.save(fafik);
        Certification certification = new Certification(fafik, "Czampion gryzienia mebli", "www.test.com");
        Certification certification1 = new Certification(fafik, "Mistrz proszenia", "www.test.url");
        List<Certification> certifications = new ArrayList<>();
        certifications.add(certification);
        certifications.add(certification1);
        //When
        List<CertificationDtoOut> testedList = certificationMapper.mapToCertificationDtoOutList(certifications);
        String testedURL = testedList.get(1).getCertificationDtoOutURL();
        String testedTitle = testedList.get(0).getCertificationDtoOutName();
        //Then
        Assert.assertEquals(testedTitle, "Czampion gryzienia mebli");
        Assert.assertEquals(testedURL, "www.test.url");
    }
}
