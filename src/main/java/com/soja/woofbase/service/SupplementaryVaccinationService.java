package com.soja.woofbase.service;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.SupplementaryVaccination;
import com.soja.woofbase.repository.SupplementaryVaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SupplementaryVaccinationService {

    private final SupplementaryVaccinationRepository supplementaryVaccinationRepository;

    @Autowired
    public SupplementaryVaccinationService(SupplementaryVaccinationRepository supplementaryVaccinationRepository) {
        this.supplementaryVaccinationRepository = supplementaryVaccinationRepository;
    }

    public List<SupplementaryVaccination> getAllSupplementaryVaccinationsByDogOwned(DogOwned dogOwned) {
        return supplementaryVaccinationRepository.findAllByDogOwned(dogOwned);
    }

    public LocalDate getDateOfNextApplicationByDogOwned(DogOwned dogOwned) {
        List<SupplementaryVaccination> allVaccinations = supplementaryVaccinationRepository.findAllByDogOwned(dogOwned);
        return allVaccinations.get(allVaccinations.size()-1).getDateOfNextApplication();
    }

    public SupplementaryVaccination save(SupplementaryVaccination supplementaryVaccination) {
        return supplementaryVaccinationRepository.save(supplementaryVaccination);
    }

    public void deleteById(Long id) {
        supplementaryVaccinationRepository.deleteById(id);
    }

    public Optional<SupplementaryVaccination> getSupplementaryById(Long id) {
        return supplementaryVaccinationRepository.findById(id);
    }
}
