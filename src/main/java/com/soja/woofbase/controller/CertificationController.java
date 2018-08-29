package com.soja.woofbase.controller;

import com.soja.woofbase.domain.Certification;
import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.dto.inbound.CertificationDtoIn;
import com.soja.woofbase.domain.dto.outbound.CertificationDtoOut;
import com.soja.woofbase.mapper.CertificationMapper;
import com.soja.woofbase.service.CertificationService;
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
@RequestMapping("/dogs/cert")
@RestController
public class CertificationController {

    private final CertificationService certificationService;

    private final CertificationMapper certificationMapper;

    private final DogOwnedService dogOwnedService;

    @Autowired
    public CertificationController(CertificationService certificationService, CertificationMapper certificationMapper,
                                   DogOwnedService dogOwnedService) {
        this.certificationService = certificationService;
        this.certificationMapper = certificationMapper;
        this.dogOwnedService = dogOwnedService;
    }

    @GetMapping(value = "{dogOwnedId}")
    public ResponseEntity<List<CertificationDtoOut>> getAllCertificationsOfDog(@PathVariable("dogOwnedId") Long dogOwnedId) {
        Optional<DogOwned> dog = dogOwnedService.getDogOwnedById(dogOwnedId);
        if (!dog.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<Certification> searchedCerts = certificationService.getAllCertificationByDog(dog.get());
        if (searchedCerts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<CertificationDtoOut> certs = certificationMapper.mapToCertificationDtoOutList(searchedCerts);
        return new ResponseEntity<>(certs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createNewCertification(@RequestBody CertificationDtoIn certificationDtoIn,
                                                       UriComponentsBuilder ucBuilder) {
        Certification cert = certificationMapper.mapToCertification(certificationDtoIn);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/dogs/cert/{id}").buildAndExpand(cert.getCertificationId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{certId}")
    public ResponseEntity<CertificationDtoOut> updateCertification(@PathVariable("certId") Long certId,
                                                                   @RequestBody CertificationDtoIn certification) {
        Optional<Certification> certToUpdate = certificationService.getCertificationById(certId);
        if (!certToUpdate.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Certification certUpdated = certificationMapper.mapToCertification(certification);
        certificationService.deleteCertificationById(certId);
        certUpdated.setCertificationId(certId);
        certificationService.save(certUpdated);
        CertificationDtoOut updatedDtoOut = certificationMapper.mapToCertificationDtoOut(certUpdated);
        return new ResponseEntity<>(updatedDtoOut, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{certId}")
    public ResponseEntity<Void> deleteCertification(@PathVariable("certId") Long certId) {
        Optional<Certification> certToDelete = certificationService.getCertificationById(certId);
        if(!certToDelete.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        certificationService.deleteCertificationById(certId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
