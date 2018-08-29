package com.soja.woofbase.controller;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.dto.inbound.DogOwnedDtoIn;
import com.soja.woofbase.domain.dto.outbound.DogOwnedDtoOut;
import com.soja.woofbase.mapper.DogOwnedMapper;
import com.soja.woofbase.service.DogOwnedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("/dogs/owned")
@RestController
public class DogOwnedController {

    private final DogOwnedMapper dogOwnedMapper;
    private final DogOwnedService dogOwnedService;

    @Autowired
    public DogOwnedController(DogOwnedMapper dogOwnedMapper, DogOwnedService dogOwnedService) {
        this.dogOwnedMapper = dogOwnedMapper;
        this.dogOwnedService = dogOwnedService;
    }


    @GetMapping
    public ResponseEntity<List<DogOwnedDtoOut>> getAllDogsOwned() {
        List<DogOwned> searchedDogs = (List)dogOwnedService.getAllDogsOwned();
        if (searchedDogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<DogOwnedDtoOut> result = dogOwnedMapper.mapToDogOwnedDtoOutList(searchedDogs);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{dogOwnedId}")
    public ResponseEntity<DogOwnedDtoOut> getDogOwned(@PathVariable("dogOwnedId") Long dogOwnedId) {
            Optional<DogOwned> searchedDog = dogOwnedService.getDogOwnedById(dogOwnedId);
            if (!searchedDog.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            DogOwnedDtoOut result = dogOwnedMapper.mapToDogOwnedDtoOut(searchedDog.get());
            return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createDogOwned(@RequestBody DogOwnedDtoIn dogOwnedDtoIn, UriComponentsBuilder uBuilder) {
        DogOwned newDogOwned = dogOwnedMapper.mapToDogOwned(dogOwnedDtoIn);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uBuilder.path("/dogs/owned/{id}").buildAndExpand(newDogOwned.getDogId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{dogOwnedId}")
    public ResponseEntity<DogOwnedDtoOut> updateDogOwned(@PathVariable("dogOwnedId") Long dogOwnedId,
                                                         @RequestBody DogOwnedDtoIn dogOwnedDtoIn) {
        Optional<DogOwned> dogToUpdate = dogOwnedService.getDogOwnedById(dogOwnedId);
        if(!dogToUpdate.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        DogOwned dogUpdated = dogOwnedMapper.mapToDogOwned(dogOwnedDtoIn);
        dogOwnedService.deleteById(dogOwnedId);
        dogUpdated.setDogId(dogOwnedId);
        dogOwnedService.save(dogUpdated);
        DogOwnedDtoOut updatedDtoOut = dogOwnedMapper.mapToDogOwnedDtoOut(dogUpdated);
        return new ResponseEntity<>(updatedDtoOut, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{dogOwnedId}")
    public ResponseEntity<Void> deleteDogOwned(@PathVariable("dogOwnedId") Long dogOwnedId) {
        Optional<DogOwned> dogToDelete = dogOwnedService.getDogOwnedById(dogOwnedId);
        if(!dogToDelete.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        dogOwnedService.deleteById(dogOwnedId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
