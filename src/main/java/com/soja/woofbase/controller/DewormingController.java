package com.soja.woofbase.controller;

import com.soja.woofbase.domain.Deworming;
import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.dto.inbound.DewormingDtoIn;
import com.soja.woofbase.domain.dto.outbound.DewormingDtoOut;
import com.soja.woofbase.mapper.DewormingMapper;
import com.soja.woofbase.service.DewormingService;
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
@RequestMapping("/dogs/dewormings")
@RestController
public class DewormingController {

    private final DewormingMapper dewormingMapper;

    private final DewormingService dewormingService;

    private final DogOwnedService dogOwnedService;

    @Autowired
    public DewormingController(DewormingMapper dewormingMapper, DewormingService dewormingService,
                               DogOwnedService dogOwnedService) {
        this.dewormingMapper = dewormingMapper;
        this.dewormingService = dewormingService;
        this.dogOwnedService = dogOwnedService;
    }

    @GetMapping(value = "{dogOwnedId}")
    public ResponseEntity<List<DewormingDtoOut>> getAllDewormingsOfDog(@PathVariable("dogOwnedId") Long dogOwnedId) {
        Optional<DogOwned> dog = dogOwnedService.getDogOwnedById(dogOwnedId);
        if (!dog.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<Deworming> searchedDewormings = dewormingService.getAllDewormingsOfDogOwned(dog.get());
        if (searchedDewormings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<DewormingDtoOut> dewormings = dewormingMapper.mapToDewormingDtoOutList(searchedDewormings);
        return new ResponseEntity<>(dewormings, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createNewDeworming(@RequestBody DewormingDtoIn dewormingDtoIn, UriComponentsBuilder ucBuilder) {
        Deworming deworming = dewormingMapper.mapToDeworming(dewormingDtoIn);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/dogs/dewormings/{id}").buildAndExpand(deworming.getDewormingId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{dewormingId}")
    public ResponseEntity<DewormingDtoOut> updateDeworming(@PathVariable("dewormingId") Long dewormingId,
                                                               @RequestBody DewormingDtoIn deworming) {
        Optional<Deworming> certToUpdate = dewormingService.getDewormingById(dewormingId);
        if (!certToUpdate.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Deworming dewormingUpdated = dewormingMapper.mapToDeworming(deworming);
        dewormingService.deleteById(dewormingId);
        dewormingUpdated.setDewormingId(dewormingId);
        dewormingService.save(dewormingUpdated);
        DewormingDtoOut updatedDtoOut = dewormingMapper.mapToDewormingDtoOut(dewormingUpdated);
        return new ResponseEntity<>(updatedDtoOut, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{dewormingId}")
    public ResponseEntity<Void> deleteDeworming(@PathVariable("dewormingId") Long dewormingId) {
        Optional<Deworming> dewormToDelete = dewormingService.getDewormingById(dewormingId);
        if (!dewormToDelete.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        dewormingService.deleteById(dewormingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}