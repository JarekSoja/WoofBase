package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.User;
import com.soja.woofbase.domain.VetVisit;
import com.soja.woofbase.domain.dto.inbound.VetVisitDtoIn;
import com.soja.woofbase.domain.dto.outbound.VetVisitDtoOut;
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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VetVisitMapperTestSuite {

    @Autowired
    private VetVisitMapper vetVisitMapper;

    @Autowired
    private DogOwnedRepository dogOwnedRepository;

    @After
    public void clearData() {
        dogOwnedRepository.deleteAll();
    }

    @Test
    public void testMapToVetVisit() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        VetVisitDtoIn vetVisitDtoIn = new VetVisitDtoIn(fafik, "www.test.url", "www.test.com");

        //When
        VetVisit testVisit = vetVisitMapper.mapToVetVisit(vetVisitDtoIn);
        String testExcerpt = testVisit.getVetVisitExcerptURL();
        String testDescription = testVisit.getVetVisitDescriptions();

        //Then
        Assert.assertEquals("www.test.url", testDescription);
        Assert.assertEquals("www.test.com", testExcerpt);

    }

    @Test
    public void testMapToVetVisitDtoOut() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        VetVisit vetVisit = new VetVisit(fafik, "www.test.url", "www.test.com");

        //When
        VetVisitDtoOut testVisit = vetVisitMapper.mapToVetVisitDtoOut(vetVisit);
        String testExcerpt = testVisit.getVetVisitExcerptURL();
        String testDescription = testVisit.getVetVisitDescriptions();

        //Then
        Assert.assertEquals("www.test.url", testDescription);
        Assert.assertEquals("www.test.com", testExcerpt);

    }

    @Test
    public void testMapToVetVisitDtoOutList() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        VetVisit vetVisit = new VetVisit(fafik, "www.test.url", "www.test.com");
        VetVisit vetVisit1 = new VetVisit(fafik, "fractured soul", "www.www.www");
        List<VetVisit> visits = new ArrayList<>();
        visits.add(vetVisit);
        visits.add(vetVisit1);

        //When
        List<VetVisitDtoOut> testList = vetVisitMapper.mapToVetVisitDtoOutList(visits);
        String testName = testList.get(1).getDogOwned().getDogName();
        String testExcerpt = testList.get(0).getVetVisitExcerptURL();
        String testDescription = testList.get(1).getVetVisitDescriptions();

        //Then
        Assert.assertEquals("fractured soul", testDescription);
        Assert.assertEquals("Fafik", testName);
        Assert.assertEquals("www.test.com", testExcerpt);
    }
}
