package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.TickPrevention;
import com.soja.woofbase.domain.User;
import com.soja.woofbase.domain.dto.inbound.TickPreventionDtoIn;
import com.soja.woofbase.domain.dto.outbound.TickPreventionDtoOut;
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
public class TickPreventionMapperTestSuite {

    @Autowired
    private TickPreventionMapper tickPreventionMapper;

    @Autowired
    private DogOwnedRepository dogOwnedRepository;

    @After
    public void clearData() {
        dogOwnedRepository.deleteAll();
    }

    @Test
    public void testMapToTickPrevention() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        TickPreventionDtoIn prevention = new TickPreventionDtoIn(
                fafik, "KillerGerms",
                LocalDate.of(2018, 4, 11),
                LocalDate.of(2018, 7, 11),
                false);

        //When
        TickPrevention testPrevention = tickPreventionMapper.mapToTickPrevention(prevention);
        String testMedication = testPrevention.getMedicationUsed();
        LocalDate testDate = testPrevention.getDateOfNextApplication();

        //Then
        Assert.assertEquals("KillerGerms", testMedication);
        Assert.assertEquals(LocalDate.of(2018,7,11), testDate);
    }

    @Test
    public void testMapToTickPreventionDtoOut() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        TickPrevention prevention = new TickPrevention(
                fafik, "KillerGerms",
                LocalDate.of(2018, 4, 11),
                LocalDate.of(2018, 7, 11),
                false);

        //When
        TickPreventionDtoOut testPrevention = tickPreventionMapper.mapToTickPreventionDtoOut(prevention);
        String testMedication = testPrevention.getMedicationUsed();
        LocalDate testDate = testPrevention.getDateOfNextApplication();

        //Then
        Assert.assertEquals("KillerGerms", testMedication);
        Assert.assertEquals(LocalDate.of(2018,7,11), testDate);
    }

    @Test
    public void testMapToSupplementaryVaccinationDtoOutList() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        TickPrevention prevention = new TickPrevention(
                fafik, "KillerGerms",
                LocalDate.of(2018, 4, 11),
                LocalDate.of(2018, 7, 11),
                false);
        TickPrevention prevention1 = new TickPrevention(
                fafik, "BugSmasher",
                LocalDate.of(2019,5,11),
                LocalDate.of(2020, 6,11),
                false);
        List<TickPrevention> preventions = new ArrayList<>();
        preventions.add(prevention);
        preventions.add(prevention1);

        //When
        List<TickPreventionDtoOut> testList = tickPreventionMapper.mapToTickPreventionDtoOutList(preventions);
        String testMedication = testList.get(1).getMedicationUsed();
        LocalDate testDate = testList.get(0).getDateOfNextApplication();

        //then
        Assert.assertEquals("BugSmasher", testMedication);
        Assert.assertEquals(LocalDate.of(2018,7,11), testDate);
    }
}
