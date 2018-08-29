package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.SupplementaryVaccination;
import com.soja.woofbase.domain.User;
import com.soja.woofbase.domain.dto.inbound.SupplementaryVaccinationDtoIn;
import com.soja.woofbase.domain.dto.outbound.SupplementaryVaccinationDtoOut;
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
public class SupplementaryVaccinationMapperTestSuite {

    @Autowired
    private SupplementaryVaccinationMapper supplementaryVaccinationMapper;

    @Autowired
    private DogOwnedRepository dogOwnedRepository;

    @After
    public void clearData() {
        dogOwnedRepository.deleteAll();
    }

    @Test
    public void testMapToSupplementaryVaccination() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        SupplementaryVaccinationDtoIn vaccination = new SupplementaryVaccinationDtoIn(
                fafik, "KillerGerms",
                LocalDate.of(2018, 4, 11),
                LocalDate.of(2018, 7, 11),
                false);

        //When
        SupplementaryVaccination testVaccination = supplementaryVaccinationMapper.mapToSupplementaryVaccination(vaccination);
        String testMedication = testVaccination.getMedicationUsed();
        LocalDate testDate = testVaccination.getDateOfNextApplication();

        //Then
        Assert.assertEquals("KillerGerms", testMedication);
        Assert.assertEquals(LocalDate.of(2018,7,11), testDate);
    }

    @Test
    public void testMapToSupplementaryVaccinationDtoOut() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        SupplementaryVaccination vaccination = new SupplementaryVaccination(
                fafik, "KillerGerms",
                LocalDate.of(2018, 4, 11),
                LocalDate.of(2018, 7, 11),
                false);

        //When
        SupplementaryVaccinationDtoOut testVaccination = supplementaryVaccinationMapper.mapToSupplementaryVaccinationDtoOut(vaccination);
        String testMedication = testVaccination.getMedicationUsed();
        LocalDate testDate = testVaccination.getDateOfNextApplication();

        //Then
        Assert.assertEquals("KillerGerms", testMedication);
        Assert.assertEquals(LocalDate.of(2018,7,11), testDate);
    }

    @Test
    public void testMapToSupplementaryVaccinationDtoOutList() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        SupplementaryVaccination vaccination = new SupplementaryVaccination(
                fafik, "KillerGerms",
                LocalDate.of(2018, 4, 11),
                LocalDate.of(2018, 7, 11),
                false);
        SupplementaryVaccination vaccination1 = new SupplementaryVaccination(
                fafik, "BugSmasher",
                LocalDate.of(2019,5,11),
                LocalDate.of(2020, 6,11),
                false);
        List<SupplementaryVaccination> vaccinations = new ArrayList<>();
        vaccinations.add(vaccination);
        vaccinations.add(vaccination1);

        //When
        List<SupplementaryVaccinationDtoOut> testList = supplementaryVaccinationMapper.mapToSupplementaryVaccinationDtoOutList(vaccinations);
        String testMedication = testList.get(1).getMedicationUsed();
        LocalDate testDate = testList.get(0).getDateOfNextApplication();

        //then
        Assert.assertEquals("BugSmasher", testMedication);
        Assert.assertEquals(LocalDate.of(2018,7,11), testDate);
    }
}
