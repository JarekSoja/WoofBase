package com.soja.woofbase.controller;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.DogShow;
import com.soja.woofbase.domain.dto.inbound.DogShowDtoIn;
import com.soja.woofbase.domain.dto.outbound.DogShowDtoOut;
import com.soja.woofbase.mapper.DogShowMapper;
import com.soja.woofbase.service.DogOwnedService;
import com.soja.woofbase.service.DogShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("/dogs/shows")
@RestController
public class DogShowController {

    private final DogOwnedService dogOwnedService;

    private final DogShowMapper dogShowMapper;

    private final DogShowService dogShowService;

    @Autowired
    public DogShowController(DogOwnedService dogOwnedService, DogShowMapper dogShowMapper, DogShowService dogShowService) {
        this.dogOwnedService = dogOwnedService;
        this.dogShowMapper = dogShowMapper;
        this.dogShowService = dogShowService;
    }

    @GetMapping(value = "{dogOwnedId}")
    public ResponseEntity<List<DogShowDtoOut>> getAllDogShowsOfDog(@PathVariable("dogOwnedId") Long dogOwnedId) {
        Optional<DogOwned> dog = dogOwnedService.getDogOwnedById(dogOwnedId);
        if (!dog.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<DogShow> searchedDogShows = dogShowService.findAllDogShowsByDog(dog.get());
        if (searchedDogShows.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<DogShowDtoOut> dogShows = dogShowMapper.mapToDogShowDtoOutList(searchedDogShows);
        return new ResponseEntity<>(dogShows, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Void> createNewDogShow(@RequestBody DogShowDtoIn dogShowDtoIn, UriComponentsBuilder ucBuilder) {
        DogShow dogShow = dogShowMapper.mapToDogShow(dogShowDtoIn);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/dogs/shows/{id}").buildAndExpand(dogShow.getDogShowId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{dogShowId}")
    public ResponseEntity<DogShowDtoOut> updateDogShow(@PathVariable("dogShowId") Long dogShowId,
                                                           @RequestBody DogShowDtoIn dogShow) {
        Optional<DogShow> dogShowToUpdate = dogShowService.findByDogShowId(dogShowId);
        if (!dogShowToUpdate.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        DogShow dogShowUpdated = dogShowMapper.mapToDogShow(dogShow);
        dogShowService.deleteById(dogShowId);
        dogShowUpdated.setDogShowId(dogShowId);
        dogShowService.save(dogShowUpdated);
        DogShowDtoOut updatedDtoOut = dogShowMapper.mapToDogShowDtoOut(dogShowUpdated);
        return new ResponseEntity<>(updatedDtoOut, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{dogShowId}")
    public ResponseEntity<Void> deleteDogShow(@PathVariable("dogShowId") Long dogShowId) {
        Optional<DogShow> dogShowToDelete = dogShowService.findByDogShowId(dogShowId);
        if (!dogShowToDelete.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        dogShowService.deleteById(dogShowId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
