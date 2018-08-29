package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.DogShow;
import com.soja.woofbase.domain.User;
import com.soja.woofbase.domain.dto.inbound.DogShowDtoIn;
import com.soja.woofbase.domain.dto.outbound.DogShowDtoOut;
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
public class DogShowMapperTestSuite {

    @Autowired
    private DogShowMapper dogShowMapper;

    @Autowired
    private DogOwnedRepository dogOwnedRepository;

    @After
    public void clearData() {
        dogOwnedRepository.deleteAll();
    }

    @Test
    public void testMapToDogShow() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        DogShowDtoIn dogShowDtoIn = new DogShowDtoIn(fafik, "www.test.url", "www.test.com");

        //When
        DogShow testShow = dogShowMapper.mapToDogShow(dogShowDtoIn);
        String testName = testShow.getDogOwned().getDogName();
        String testUrl1 = testShow.getDogShowCatalogueURL();
        String testUrl2 = testShow.getDogShowResultsURL();

        //Then
        Assert.assertEquals("Fafik", testName);
        Assert.assertEquals("www.test.com", testUrl1);
        Assert.assertEquals("www.test.url", testUrl2);
    }

    @Test
    public void testMapToDogShowDtoOut() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        DogShow dogShow = new DogShow(fafik, "www.test.url", "www.test.com");

        //When
        DogShowDtoOut testShow = dogShowMapper.mapToDogShowDtoOut(dogShow);
        String testName = testShow.getDogOwned().getDogName();
        String testUrl1 = testShow.getDogShowCatalogueURL();
        String testUrl2 = testShow.getDogShowResultsURL();

        //Then
        Assert.assertEquals("Fafik", testName);
        Assert.assertEquals("www.test.com", testUrl1);
        Assert.assertEquals("www.test.url", testUrl2);
    }

    @Test
    public void testMapToDogShowDtoOutList() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        DogShow dogShow = new DogShow(fafik, "www.test.url", "www.test.com");
        DogShow dogShow2 = new DogShow(fafik, "www.test2.url", "www.test2.com");
        List<DogShow> dogShows = new ArrayList<>();
        dogShows.add(dogShow);
        dogShows.add(dogShow2);

        //When
        List<DogShowDtoOut> testList = dogShowMapper.mapToDogShowDtoOutList(dogShows);
        String testName = testList.get(0).getDogOwned().getDogName();
        String testUrl1 = testList.get(1).getDogShowCatalogueURL();
        String testUrl2 = testList.get(1).getDogShowResultsURL();

        //Then
        Assert.assertEquals("Fafik", testName);
        Assert.assertEquals("www.test2.com", testUrl1);
        Assert.assertEquals("www.test2.url", testUrl2);
    }
}
