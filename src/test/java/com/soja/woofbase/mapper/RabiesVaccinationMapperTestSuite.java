package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.RabiesVaccination;
import com.soja.woofbase.domain.User;
import com.soja.woofbase.domain.dto.inbound.RabiesVaccinationDtoIn;
import com.soja.woofbase.domain.dto.outbound.RabiesVaccinationDtoOut;
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
public class RabiesVaccinationMapperTestSuite {

    @Autowired
    RabiesVaccinationMapper rabiesVaccinationMapper;

    @Autowired
    DogOwnedRepository dogOwnedRepository;

    @After
    public void clearData() {
        dogOwnedRepository.deleteAll();
    }

    @Test
    public void testMapToRabiesVaccination() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        RabiesVaccinationDtoIn rabiesVaccinationDtoIn = new RabiesVaccinationDtoIn(
                fafik,
                "Medication",
                LocalDate.of(2011,2,3),
                LocalDate.of(2012, 3, 2),
                false);

        //When
        RabiesVaccination testVaccination = rabiesVaccinationMapper.mapToRabiesVaccination(rabiesVaccinationDtoIn);
        String testMedication = testVaccination.getMedicationUsed();
        LocalDate testDate = testVaccination.getDateOfNextApplication();
        String testName = testVaccination.getDogOwned().getDogName();

        //Then
        Assert.assertEquals("Medication", testMedication);
        Assert.assertEquals("Fafik", testName);
        Assert.assertEquals(LocalDate.of(2012,3,2), testDate);
    }

    @Test
    public void testMapToRabiesVaccinationDtoOut() {
        //Given
        DogOwned fafik = new DogOwned(new User(), "Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        RabiesVaccination rabiesVaccination = new RabiesVaccination(
                fafik,
                "Medication",
                LocalDate.of(2011,2,3),
                LocalDate.of(2012, 3, 2),
                false);

        //When
        RabiesVaccinationDtoOut testVaccination = rabiesVaccinationMapper.mapToRabiesVaccinationDtoOut(rabiesVaccination);
        String testMedication = testVaccination.getMedicationUsed();
        LocalDate testDate = testVaccination.getDateOfNextApplication();
        String testName = testVaccination.getDogOwned().getDogName();

        //Then
        Assert.assertEquals("Medication", testMedication);
        Assert.assertEquals("Fafik", testName);
        Assert.assertEquals(LocalDate.of(2012,3,2), testDate);
    }

    @Test
    public void testMapToRabiesVaccinationDtoOutList() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        RabiesVaccination rabiesVaccination = new RabiesVaccination(
                fafik,
                "Medication",
                LocalDate.of(2011,2,3),
                LocalDate.of(2012, 3, 2),
                false);
        RabiesVaccination rabiesVaccination1 = new RabiesVaccination(
                fafik,
                "Medication",
                LocalDate.of(2014,11,3),
                LocalDate.of(2016, 2, 22),
                false);
        List<RabiesVaccination> listRabies = new ArrayList<>();
        listRabies.add(rabiesVaccination);
        listRabies.add(rabiesVaccination1);

        //When
        List<RabiesVaccinationDtoOut> testList = rabiesVaccinationMapper.mapToRabiesVaccinationDtoOutList(listRabies);
        String testMedication = testList.get(1).getMedicationUsed();
        LocalDate testDate1 = testList.get(0).getDateOfApplication();
        LocalDate testDate2 = testList.get(1).getDateOfNextApplication();

        //Then
        Assert.assertEquals("Medication", testMedication);
        Assert.assertEquals(LocalDate.of(2011,2,3), testDate1);
        Assert.assertEquals(LocalDate.of(2016,2,22), testDate2);
    }
}
