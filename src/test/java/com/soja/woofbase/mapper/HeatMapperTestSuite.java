package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.Heat;
import com.soja.woofbase.domain.User;
import com.soja.woofbase.domain.dto.inbound.HeatDtoIn;
import com.soja.woofbase.domain.dto.outbound.HeatDtoOut;
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
public class HeatMapperTestSuite {

    @Autowired
    HeatMapper heatMapper;

    @Autowired
    private DogOwnedRepository dogOwnedRepository;

    @After
    public void clearData() {
        dogOwnedRepository.deleteAll();
    }


    @Test
    public void testMapToHeat() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        HeatDtoIn heatDtoIn = new HeatDtoIn(
                fafik,
                LocalDate.of(2018, 7, 11),
                LocalDate.of(2018, 8, 22));

        //When
        Heat testHeat = heatMapper.mapToHeat(heatDtoIn);
        boolean testSex = testHeat.getDogOwned().getDogSex();
        LocalDate testDate = testHeat.getEndDate();

        //Them
        Assert.assertFalse(testSex);
        Assert.assertEquals(LocalDate.of(2018, 8, 22), testDate);
    }

    @Test
    public void testMapToHeatDtoOut() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        Heat heat = new Heat(
                fafik,
                LocalDate.of(2018, 7, 11),
                LocalDate.of(2018, 8, 22));

        //When
        HeatDtoOut testHeat = heatMapper.mapToHeatDtoOut(heat);
        boolean testSex = testHeat.getDogOwned().getDogSex();
        LocalDate testDate = testHeat.getEndDate();

        //Them
        Assert.assertFalse(testSex);
        Assert.assertEquals(LocalDate.of(2018, 8, 22), testDate);
    }

    @Test
    public void testMapToHeatDtoOutList() {
        //Given
        DogOwned fafik = new DogOwned(new User(),"Fafik", "bialy", false, LocalDate.of(2011, 3, 11));
        dogOwnedRepository.save(fafik);
        Heat heat = new Heat(
                fafik,
                LocalDate.of(2018, 7,11),
                LocalDate.of(2018,8,22));
        Heat heat2 = new Heat(
                fafik,
                LocalDate.of(2019, 3,11),
                LocalDate.of(2019,3,30));
        List<Heat> heats = new ArrayList<>();
        heats.add(heat);
        heats.add(heat2);

        //When
        List<HeatDtoOut> testHeats = heatMapper.mapToHeatDtoOutList(heats);
        LocalDate testDate = testHeats.get(1).getEndDate();
        LocalDate testDate2 = testHeats.get(0).getStartDate();

        //Then
        Assert.assertEquals(LocalDate.of(2019,3,30), testDate);
        Assert.assertEquals(LocalDate.of(2018,7,11), testDate2);

    }
}

