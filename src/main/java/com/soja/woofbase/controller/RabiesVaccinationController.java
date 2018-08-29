package com.soja.woofbase.controller;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.RabiesVaccination;
import com.soja.woofbase.domain.dto.inbound.RabiesVaccinationDtoIn;
import com.soja.woofbase.domain.dto.outbound.RabiesVaccinationDtoOut;
import com.soja.woofbase.mapper.RabiesVaccinationMapper;
import com.soja.woofbase.service.DogOwnedService;
import com.soja.woofbase.service.RabiesVaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("dogs/rabies")
@RestController
public class RabiesVaccinationController {

    private final DogOwnedService dogOwnedService;

    private final RabiesVaccinationMapper rabiesVaccinationMapper;

    private final RabiesVaccinationService rabiesVaccinationService;

    @Autowired
    public RabiesVaccinationController(DogOwnedService dogOwnedService,
                                       RabiesVaccinationMapper rabiesVaccinationMapper,
                                       RabiesVaccinationService rabiesVaccinationService) {
        this.dogOwnedService = dogOwnedService;
        this.rabiesVaccinationMapper = rabiesVaccinationMapper;
        this.rabiesVaccinationService = rabiesVaccinationService;
    }

    @GetMapping(value = "{dogOwnedId}")
    public ResponseEntity<List<RabiesVaccinationDtoOut>> getAllRabiesVaccByDog(@PathVariable("dogOwnedId") Long dogOwnedId) {
        Optional<DogOwned> dog = dogOwnedService.getDogOwnedById(dogOwnedId);
        if (!dog.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<RabiesVaccination> rabies = rabiesVaccinationService.getAllRabiesVaccinationsByDogOwned(dog.get());
        if (rabies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<RabiesVaccinationDtoOut> rabiesDtoOut = rabiesVaccinationMapper.mapToRabiesVaccinationDtoOutList(rabies);
        return new ResponseEntity<>(rabiesDtoOut, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createNewRabiesVacc(@RequestBody RabiesVaccinationDtoIn rabiesDtoIn,
                                                    UriComponentsBuilder ucBuilder) {
        RabiesVaccination rabies = rabiesVaccinationMapper.mapToRabiesVaccination(rabiesDtoIn);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/dogs/rabies/{id}").buildAndExpand(rabies.getRabiesVaccinationId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{rabiesId}")
    public ResponseEntity<RabiesVaccinationDtoOut> updateRabiesVacc(@PathVariable("rabiesId") Long rabiesId,
                                                                    @RequestBody RabiesVaccinationDtoIn rabiesDtoIn) {
        Optional<RabiesVaccination> rabiesToUpdate = rabiesVaccinationService.getRabiesVaccinationById(rabiesId);
        if (!rabiesToUpdate.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        RabiesVaccination rabiesUpdated = rabiesVaccinationMapper.mapToRabiesVaccination(rabiesDtoIn);
        rabiesVaccinationService.deleteById(rabiesId);
        rabiesUpdated.setRabiesVaccinationId(rabiesId);
        rabiesVaccinationService.save(rabiesUpdated);
        RabiesVaccinationDtoOut updatedDtoOut = rabiesVaccinationMapper.mapToRabiesVaccinationDtoOut(rabiesUpdated);
        return new ResponseEntity<>(updatedDtoOut, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{rabiesId}")
    public ResponseEntity<Void> deleteRabiesVacc(@PathVariable("rabiesId") Long rabiesId) {
        Optional<RabiesVaccination> rabiesToDelete = rabiesVaccinationService.getRabiesVaccinationById(rabiesId);
        if (!rabiesToDelete.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        rabiesVaccinationService.deleteById(rabiesId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
