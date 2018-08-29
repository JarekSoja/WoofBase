package com.soja.woofbase.service;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.TickPrevention;
import com.soja.woofbase.repository.TickPreventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TickPreventionService {


    private final TickPreventionRepository tickPreventionRepository;

    @Autowired
    public TickPreventionService(TickPreventionRepository tickPreventionRepository) {
        this.tickPreventionRepository = tickPreventionRepository;
    }

    public List<TickPrevention> getAllTickPreventionInfoByDogOwned(DogOwned dogOwned) {
        return tickPreventionRepository.findAllByDogOwned(dogOwned);
    }

    public LocalDate getDateOfNextApplicationByDogOwned(DogOwned dogOwned) {
        List<TickPrevention> allApplications = tickPreventionRepository.findAllByDogOwned(dogOwned);
        return allApplications.get(allApplications.size()-1).getDateOfNextApplication();
    }

    public TickPrevention save(TickPrevention tickPrevention) {
        return tickPreventionRepository.save(tickPrevention);
    }

    public void deleteById(Long id) {
        tickPreventionRepository.deleteById(id);
    }

    public Optional<TickPrevention> getTickPreventionById(Long id) {
        return tickPreventionRepository.findById(id);
    }
}
