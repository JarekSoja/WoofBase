package com.soja.woofbase.service;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.RabiesVaccination;
import com.soja.woofbase.repository.RabiesVaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RabiesVaccinationService {

    private final RabiesVaccinationRepository rabiesVaccinationRepository;

    @Autowired
    public RabiesVaccinationService(RabiesVaccinationRepository rabiesVaccinationRepository) {
        this.rabiesVaccinationRepository = rabiesVaccinationRepository;
    }

    public List<RabiesVaccination> getAllRabiesVaccinationsByDogOwned(DogOwned dogOwned) {
        return rabiesVaccinationRepository.findAllByDogOwned(dogOwned);
    }

    public LocalDate getDateOfNextRabiesVaccinationByDogOwned(DogOwned dogOwned) {
        List<RabiesVaccination> rabiesVaccinations = rabiesVaccinationRepository.findAllByDogOwned(dogOwned);
        return rabiesVaccinations.get(rabiesVaccinations.size()-1).getDateOfNextApplication();
    }

    public RabiesVaccination save(RabiesVaccination rabiesVaccination) {
        return rabiesVaccinationRepository.save(rabiesVaccination);
    }

    public void deleteById(Long id) {
        rabiesVaccinationRepository.deleteById(id);
    }

    public Optional<RabiesVaccination> getRabiesVaccinationById(Long id) {
        return rabiesVaccinationRepository.findById(id);
    }
}
