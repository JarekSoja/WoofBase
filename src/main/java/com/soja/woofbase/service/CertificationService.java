package com.soja.woofbase.service;

import com.soja.woofbase.domain.Certification;
import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.repository.CertificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificationService {

    private final CertificationRepository certificationRepository;

    @Autowired
    public CertificationService(CertificationRepository certificationRepository) {
        this.certificationRepository = certificationRepository;
    }

    public Optional<Certification> getCertificationById(Long id) {
        return certificationRepository.findById(id);
    }

    public List<Certification> getAllCertificationByDog(DogOwned dogOwned) {
        return certificationRepository.findAllByDogOwned(dogOwned);
    }

    public Certification save(Certification certification) {
        return certificationRepository.save(certification);
    }

    public void deleteCertificationById(Long id) {
        certificationRepository.deleteById(id);
    }
}
