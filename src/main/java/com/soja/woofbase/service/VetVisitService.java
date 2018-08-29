package com.soja.woofbase.service;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.VetVisit;
import com.soja.woofbase.repository.VetVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VetVisitService {


    private final VetVisitRepository vetVisitRepository;

    @Autowired
    public VetVisitService(VetVisitRepository vetVisitRepository) {
        this.vetVisitRepository = vetVisitRepository;
    }

    public List<VetVisit> getAllVisitsByDogOwned(DogOwned dogOwned) {
        return vetVisitRepository.findAllByDogOwned(dogOwned);
    }


    public VetVisit save(VetVisit vetVisit) {
        return vetVisitRepository.save(vetVisit);
    }

    public void deleteById(Long id) {
        vetVisitRepository.deleteById(id);
    }

    public Optional<VetVisit> getVetVisitById(Long id) {
        return vetVisitRepository.findById(id);
    }


}
