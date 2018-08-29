package com.soja.woofbase.service;

import com.soja.woofbase.validate.ObjectNotFoundException;
import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.Heat;
import com.soja.woofbase.repository.HeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HeatService {

    private final HeatRepository heatRepository;

    @Autowired
    public HeatService(HeatRepository heatRepository) {
        this.heatRepository = heatRepository;
    }

    public List<Heat> getAllHeatsByDogOwned(DogOwned dogOwned) {
        return heatRepository.findAllByDogOwned(dogOwned);
    }

    public LocalDate getStartDateOfLastHeatByDogOwned(DogOwned dogOwned) {
        List<Heat> allHeats = heatRepository.findAllByDogOwned(dogOwned);
        return allHeats.get(allHeats.size() - 1).getStartDate();
    }

    public LocalDate getEndDateOfLastHeatByDogOwned(DogOwned dogOwned) throws ObjectNotFoundException {
        List<Heat> allHeats = heatRepository.findAllByDogOwned(dogOwned);
        LocalDate result = allHeats.get(allHeats.size() - 1).getEndDate();
        if (!result.equals(null)) {
            return result;
        } else {
            throw new ObjectNotFoundException();
        }
    }

    public Heat save(Heat heat) {
        return heatRepository.save(heat);
    }

    public void deleteById(Long id) {
        heatRepository.deleteById(id);
    }

    public Optional<Heat> findByHeatId(Long id) {
        return heatRepository.findById(id);
    }
}
