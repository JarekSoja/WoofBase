package com.soja.woofbase.controller;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.Heat;
import com.soja.woofbase.domain.dto.inbound.HeatDtoIn;
import com.soja.woofbase.domain.dto.outbound.HeatDtoOut;
import com.soja.woofbase.mapper.HeatMapper;
import com.soja.woofbase.service.DogOwnedService;
import com.soja.woofbase.service.HeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("/dogs/heats")
@RestController
public class HeatController {

    private final DogOwnedService dogOwnedService;

    private final HeatMapper heatMapper;

    private final HeatService heatService;

    @Autowired
    public HeatController(DogOwnedService dogOwnedService, HeatMapper heatMapper, HeatService heatService) {
        this.dogOwnedService = dogOwnedService;
        this.heatMapper = heatMapper;
        this.heatService = heatService;
    }

    @GetMapping(value = "{dogOwnedId}")
    public ResponseEntity<List<HeatDtoOut>> getAllHeatsByDog(@PathVariable("dogOwnedId") Long dogOwnedId) {
        Optional<DogOwned> dog = dogOwnedService.getDogOwnedById(dogOwnedId);
        if (!dog.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<Heat> heats = heatService.getAllHeatsByDogOwned(dog.get());
        if (heats.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<HeatDtoOut> heatsDtoOut = heatMapper.mapToHeatDtoOutList(heats);
        return new ResponseEntity<>(heatsDtoOut, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createNewHeat(@RequestBody HeatDtoIn heatDtoIn, UriComponentsBuilder ucBuilder) {
        Heat heat = heatMapper.mapToHeat(heatDtoIn);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/dogs/heats/{id}").buildAndExpand(heat.getHeatId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{heatId}")
    public ResponseEntity<HeatDtoOut> updateHeat(@PathVariable("heatId") Long heatId, @RequestBody HeatDtoIn heat) {
        Optional<Heat> heatToUpdate = heatService.findByHeatId(heatId);
        if (!heatToUpdate.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Heat heatUpdated = heatMapper.mapToHeat(heat);
        heatService.deleteById(heatId);
        heatUpdated.setHeatId(heatId);
        heatService.save(heatUpdated);
        HeatDtoOut updatedDtoOut = heatMapper.mapToHeatDtoOut(heatUpdated);
        return new ResponseEntity<>(updatedDtoOut, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{heatId}")
    public ResponseEntity<Void> deleteHeat(@PathVariable("heatId") Long heatId) {
        Optional<Heat> heatToDelete = heatService.findByHeatId(heatId);
        if (!heatToDelete.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        heatService.deleteById(heatId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
