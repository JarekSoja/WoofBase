package com.soja.woofbase.controller;

import com.soja.woofbase.domain.dto.inbound.SupplementaryVaccinationDtoIn;
import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.SupplementaryVaccination;
import com.soja.woofbase.domain.dto.outbound.SupplementaryVaccinationDtoOut;
import com.soja.woofbase.mapper.SupplementaryVaccinationMapper;
import com.soja.woofbase.service.DogOwnedService;
import com.soja.woofbase.service.SupplementaryVaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("dogs/supplvacc")
@RestController
public class SupplementaryVaccinationController {

    private final DogOwnedService dogOwnedService;

    private final SupplementaryVaccinationMapper supplementaryVaccinationMapper;

    private final SupplementaryVaccinationService supplementaryVaccinationService;

    @Autowired
    public SupplementaryVaccinationController(DogOwnedService dogOwnedService,
                                              SupplementaryVaccinationMapper supplementaryVaccinationMapper,
                                              SupplementaryVaccinationService supplementaryVaccinationService)
    {
        this.dogOwnedService = dogOwnedService;
        this.supplementaryVaccinationMapper = supplementaryVaccinationMapper;
        this.supplementaryVaccinationService = supplementaryVaccinationService;
    }

    @GetMapping(value = "{dogOwnedId}")
    public ResponseEntity<List<SupplementaryVaccinationDtoOut>> getAllSupplementaryVaccByDog
            (@PathVariable("dogOwnedId") Long dogOwnedId) {
        Optional<DogOwned> dog = dogOwnedService.getDogOwnedById(dogOwnedId);
        if (!dog.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<SupplementaryVaccination> supplementary =
                supplementaryVaccinationService.getAllSupplementaryVaccinationsByDogOwned(dog.get());
        if (supplementary.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<SupplementaryVaccinationDtoOut> supplDtoOut =
                supplementaryVaccinationMapper.mapToSupplementaryVaccinationDtoOutList(supplementary);
        return new ResponseEntity<>(supplDtoOut, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createNewSupplementaryVacc(@RequestBody SupplementaryVaccinationDtoIn supplDtoIn,
                                                    UriComponentsBuilder ucBuilder) {
        SupplementaryVaccination supplementary = supplementaryVaccinationMapper.mapToSupplementaryVaccination(supplDtoIn);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/dogs/supplvac/{id}")
                .buildAndExpand(supplementary.getSupplementaryVaccinationId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{supplvaccId}")
    public ResponseEntity<SupplementaryVaccinationDtoOut> updateSupplementaryVacc
            (@PathVariable("supplvaccId") Long suplId, @RequestBody SupplementaryVaccinationDtoIn supplDtoIn) {

        Optional<SupplementaryVaccination> supplToUpdate = supplementaryVaccinationService.getSupplementaryById(suplId);
        if (!supplToUpdate.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        SupplementaryVaccination supplUpdated = supplementaryVaccinationMapper.mapToSupplementaryVaccination(supplDtoIn);
        supplementaryVaccinationService.deleteById(suplId);
        supplUpdated.setSupplementaryVaccinationId(suplId);
        supplementaryVaccinationService.save(supplUpdated);
        SupplementaryVaccinationDtoOut updatedDtoOut =
                supplementaryVaccinationMapper.mapToSupplementaryVaccinationDtoOut(supplUpdated);
        return new ResponseEntity<>(updatedDtoOut, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{supplvaccId}")
    public ResponseEntity<Void> deleteSupplementaryVacc(@PathVariable("supplvaccId") Long supplId) {
        Optional<SupplementaryVaccination> supplToDelete = supplementaryVaccinationService.getSupplementaryById(supplId);
        if (!supplToDelete.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        supplementaryVaccinationService.deleteById(supplId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
