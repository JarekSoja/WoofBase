package com.soja.woofbase.controller;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.TickPrevention;
import com.soja.woofbase.domain.dto.inbound.TickPreventionDtoIn;
import com.soja.woofbase.domain.dto.outbound.TickPreventionDtoOut;
import com.soja.woofbase.mapper.TickPreventionMapper;
import com.soja.woofbase.service.DogOwnedService;
import com.soja.woofbase.service.TickPreventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("/dogs/ticks")
@RestController
public class TickPreventionController {

    private final DogOwnedService dogOwnedService;

    private final TickPreventionMapper tickPreventionMapper;

    private final TickPreventionService tickPreventionService;

    @Autowired
    public TickPreventionController(DogOwnedService dogOwnedService, TickPreventionMapper tickPreventionMapper,
                                    TickPreventionService tickPreventionService) {
        this.dogOwnedService = dogOwnedService;
        this.tickPreventionMapper = tickPreventionMapper;
        this.tickPreventionService = tickPreventionService;
    }

    @GetMapping(value = "{dogOwnedId}")
    public ResponseEntity<List<TickPreventionDtoOut>> getAllTickPreventionsByDog
            (@PathVariable("dogOwnedId") Long dogOwnedId) {

        Optional<DogOwned> dog = dogOwnedService.getDogOwnedById(dogOwnedId);
        if (!dog.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<TickPrevention> prevention =
                tickPreventionService.getAllTickPreventionInfoByDogOwned(dog.get());
        if (prevention.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<TickPreventionDtoOut> tickDtoOut =
                tickPreventionMapper.mapToTickPreventionDtoOutList(prevention);
        return new ResponseEntity<>(tickDtoOut, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createNewTickPrevention(@RequestBody TickPreventionDtoIn tickDtoIn,
                                                           UriComponentsBuilder ucBuilder) {
        TickPrevention prevention = tickPreventionMapper.mapToTickPrevention(tickDtoIn);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/dogs/ticks/{id}")
                .buildAndExpand(prevention.getTickPreventionId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{ticksId}")
    public ResponseEntity<TickPreventionDtoOut> updateTickPrevention
            (@PathVariable("ticksId") Long tickId, @RequestBody TickPreventionDtoIn tickDtoIn) {

        Optional<TickPrevention> tickToUpdate = tickPreventionService.getTickPreventionById(tickId);
        if (!tickToUpdate.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        TickPrevention tickUpdated = tickPreventionMapper.mapToTickPrevention(tickDtoIn);
        tickPreventionService.deleteById(tickId);
        tickUpdated.setTickPreventionId(tickId);
        tickPreventionService.save(tickUpdated);
        TickPreventionDtoOut updatedDtoOut =
                tickPreventionMapper.mapToTickPreventionDtoOut(tickUpdated);
        return new ResponseEntity<>(updatedDtoOut, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{tickId}")
    public ResponseEntity<Void> deleteTickPrevention(@PathVariable("tickId") Long tickId) {
        Optional<TickPrevention> tickToDelete = tickPreventionService.getTickPreventionById(tickId);
        if (!tickToDelete.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tickPreventionService.deleteById(tickId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
