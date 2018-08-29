package com.soja.woofbase.service;

import com.soja.woofbase.validate.ObjectNotFoundException;
import com.soja.woofbase.domain.Deworming;
import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.repository.DewormingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DewormingService {

    private final DewormingRepository dewormingRepository;

    @Autowired
    public DewormingService(DewormingRepository dewormingRepository) {
        this.dewormingRepository = dewormingRepository;
    }

    public Optional<Deworming> getDewormingById(Long id) {
        return dewormingRepository.findById(id);
    }

    public List<Deworming> getAllDewormingsOfDogOwned(DogOwned dogOwned) {
        return dewormingRepository.findAllByDogOwned(dogOwned);
    }

    public LocalDate getDateOfNextApplication(DogOwned dogOwned) {
        List<Deworming> allDewormings = dewormingRepository.findAllByDogOwned(dogOwned);
        Deworming lastDeworming = allDewormings.get(allDewormings.size() - 1);
        return lastDeworming.getDateOfNextApplication();
    }

    public Deworming save(Deworming deworming) {
        return dewormingRepository.save(deworming);
    }

    public void deleteById(Long id) {
        dewormingRepository.deleteById(id);
    }
}
