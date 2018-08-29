package com.soja.woofbase.controller;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.VetVisit;
import com.soja.woofbase.domain.dto.inbound.VetVisitDtoIn;
import com.soja.woofbase.domain.dto.outbound.VetVisitDtoOut;
import com.soja.woofbase.mapper.VetVisitMapper;
import com.soja.woofbase.service.DogOwnedService;
import com.soja.woofbase.service.VetVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("/dogs/visits")
@RestController
public class VetVisitController {

    private final DogOwnedService dogOwnedService;

    private final VetVisitMapper vetVisitMapper;

    private final VetVisitService vetVisitService;

    @Autowired
    public VetVisitController(DogOwnedService dogOwnedService, VetVisitMapper vetVisitMapper,
                              VetVisitService vetVisitService) {
        this.dogOwnedService = dogOwnedService;
        this.vetVisitMapper = vetVisitMapper;
        this.vetVisitService = vetVisitService;
    }


    @GetMapping(value = "{dogOwnedId}")
    public ResponseEntity<List<VetVisitDtoOut>> getAllVetVisitsByDog(@PathVariable("dogOwnedId") Long dogOwnedId) {
        Optional<DogOwned> dog = dogOwnedService.getDogOwnedById(dogOwnedId);
        if (!dog.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<VetVisit> visits =
                vetVisitService.getAllVisitsByDogOwned(dog.get());
        if (visits.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<VetVisitDtoOut> visitsDtoOut =
                vetVisitMapper.mapToVetVisitDtoOutList(visits);
        return new ResponseEntity<>(visitsDtoOut, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createNewVetVisit(@RequestBody VetVisitDtoIn visitDtoIn,
                                                  UriComponentsBuilder ucBuilder) {
        VetVisit visit = vetVisitMapper.mapToVetVisit(visitDtoIn);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/dogs/visits/{id}")
                .buildAndExpand(visit.getVetVisitId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{visitId}")
    public ResponseEntity<VetVisitDtoOut> updateTickPrevention
            (@PathVariable("visitId") Long visitId, @RequestBody VetVisitDtoIn visitDtoIn) {

        Optional<VetVisit> visitToUpdate = vetVisitService.getVetVisitById(visitId);
        if (!visitToUpdate.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        VetVisit visitUpdated = vetVisitMapper.mapToVetVisit(visitDtoIn);
        vetVisitService.deleteById(visitId);
        visitUpdated.setVetVisitId(visitId);
        vetVisitService.save(visitUpdated);
        VetVisitDtoOut updatedDtoOut =
                vetVisitMapper.mapToVetVisitDtoOut(visitUpdated);
        return new ResponseEntity<>(updatedDtoOut, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{visitId}")
    public ResponseEntity<Void> deleteVetVisit(@PathVariable("visitId") Long visitId) {
        Optional<VetVisit> visitToDelete = vetVisitService.getVetVisitById(visitId);
        if (!visitToDelete.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        vetVisitService.deleteById(visitId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
