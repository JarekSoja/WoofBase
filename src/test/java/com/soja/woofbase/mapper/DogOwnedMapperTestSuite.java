package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.User;
import com.soja.woofbase.domain.dto.inbound.DogOwnedDtoIn;
import com.soja.woofbase.domain.dto.outbound.DogOwnedDtoOut;
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
public class DogOwnedMapperTestSuite {

    @Autowired
    private DogOwnedMapper dogOwnedMapper;

    @Autowired
    private DogOwnedRepository dogOwnedRepository;

    @After
    public void clearData() {
        dogOwnedRepository.deleteAll();
    }

    @Test
    public void testMapToDogOwned() {
        //Given
        DogOwnedDtoIn fafik = new DogOwnedDtoIn("Fafik", "bialy", false, LocalDate.of(2011, 3,11));

        //When
        DogOwned testDog = dogOwnedMapper.mapToDogOwned(fafik);
        String testName = testDog.getDogName();
        boolean testSex = testDog.getDogSex();
        LocalDate testDate = testDog.getDateOfBirth();

        //Then
        Assert.assertEquals("Fafik", testName);
        Assert.assertFalse(testSex);
        Assert.assertEquals(LocalDate.of(2011, 3, 11), testDate);
    }

    @Test
    public void testMapToDogOwnedDtoOut() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3,11));

        //When
        DogOwnedDtoOut testDog = dogOwnedMapper.mapToDogOwnedDtoOut(fafik);
        String testName = testDog.getDogName();
        boolean testSex = testDog.getDogSex();
        LocalDate testDate = testDog.getDateOfBirth();

        //Then
        Assert.assertEquals("Fafik", testName);
        Assert.assertFalse(testSex);
        Assert.assertEquals(LocalDate.of(2011, 3, 11), testDate);
    }

    @Test
    public void testMapToDogOwnedDtoOutList() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3,11));
        DogOwned azor = new DogOwned(new User(),"Azor", "czarny", false, LocalDate.of(2013, 3,21));
        List<DogOwned> dogs = new ArrayList<>();
        dogs.add(fafik);
        dogs.add(azor);

        //When
        List<DogOwnedDtoOut> testList = dogOwnedMapper.mapToDogOwnedDtoOutList(dogs);
        String testName = testList.get(0).getDogName();
        boolean testSex = testList.get(1).getDogSex();
        LocalDate testDate = testList.get(1).getDateOfBirth();

        //Then
        Assert.assertEquals("Fafik", testName);
        Assert.assertFalse(testSex);
        Assert.assertEquals(LocalDate.of(2013, 3, 21), testDate);

    }
}
