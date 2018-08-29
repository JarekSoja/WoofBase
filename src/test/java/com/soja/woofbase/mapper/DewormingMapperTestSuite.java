package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.Deworming;
import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.User;
import com.soja.woofbase.domain.dto.inbound.DewormingDtoIn;
import com.soja.woofbase.domain.dto.outbound.DewormingDtoOut;
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
public class DewormingMapperTestSuite {

    @Autowired
    DewormingMapper dewormingMapper;

    @Autowired
    private DogOwnedRepository dogOwnedRepository;

    @After
    public void clearData() {
        dogOwnedRepository.deleteAll();
    }


    @Test
    public void testMapToDeworming() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        DewormingDtoIn dewormingDtoIn = new DewormingDtoIn(
                fafik, "KillerGerms",
                LocalDate.of(2018, 4, 11),
                LocalDate.of(2018, 7, 11),
                false);

        //When
        Deworming deworming = dewormingMapper.mapToDeworming(dewormingDtoIn);
        String testDogName = deworming.getDogOwned().getDogName();
        String testMedicationName = deworming.getMedicationUsed();
        LocalDate testDate = deworming.getDateOfApplication();

        //Then
        Assert.assertEquals("Fafik", testDogName);
        Assert.assertEquals("KillerGerms", testMedicationName);
        Assert.assertEquals(LocalDate.of(2018, 4, 11), testDate);
    }

    @Test
    public void testMapToDewormingDtoOut() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        Deworming deworming = new Deworming(
                fafik, "KillerGerms",
                LocalDate.of(2018, 4, 11),
                LocalDate.of(2018, 7, 11),
                false);

        //When
        DewormingDtoOut dewormingDtoOut = dewormingMapper.mapToDewormingDtoOut(deworming);
        String testDogName = dewormingDtoOut.getDogOwned().getDogName();
        String testMedicationName = dewormingDtoOut.getMedicationDtoOutUsed();
        LocalDate testDate = dewormingDtoOut.getDateOfApplicationDtoOut();

        //Then
        Assert.assertEquals("Fafik", testDogName);
        Assert.assertEquals("KillerGerms", testMedicationName);
        Assert.assertEquals(LocalDate.of(2018, 4, 11), testDate);
    }

    @Test
    public void testMapToDewormingDtoOutList() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        Deworming deworming = new Deworming(
                fafik, "KillerGerms",
                LocalDate.of(2018, 4, 11),
                LocalDate.of(2018, 7, 11),
                false);
        Deworming deworming1 = new Deworming(
                fafik, "BugShield",
                LocalDate.of(2018, 4, 11),
                LocalDate.of(2018, 7, 11),
                false);
        List<Deworming> dewormings = new ArrayList<>();
        dewormings.add(deworming);
        dewormings.add(deworming1);

        //When
        List<DewormingDtoOut> testList = dewormingMapper.mapToDewormingDtoOutList(dewormings);
        String testName = testList.get(0).getMedicationDtoOutUsed();
        String testName2 = testList.get(1).getMedicationDtoOutUsed();
        LocalDate testDate = testList.get(1).getDateOfNextApplicationDtoOut();

        //Then
        Assert.assertEquals("KillerGerms", testName);
        Assert.assertEquals("BugShield", testName2);
        Assert.assertEquals(LocalDate.of(2018, 7, 11), testDate);
    }
}
