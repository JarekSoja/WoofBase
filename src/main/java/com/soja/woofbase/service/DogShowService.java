package com.soja.woofbase.service;

import com.soja.woofbase.domain.Dog;
import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.DogShow;
import com.soja.woofbase.repository.DogShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogShowService {

    private final DogShowRepository dogShowRepository;

    @Autowired
    public DogShowService(DogShowRepository dogShowRepository) {
        this.dogShowRepository = dogShowRepository;
    }

    public List<DogShow> findAllDogShowsByDog(DogOwned dogOwned) {
        return dogShowRepository.findAllByDogOwned(dogOwned);
    }

    public DogShow save(DogShow dogShow) {
        return dogShowRepository.save(dogShow);
    }

    public void deleteById(Long id) {
        dogShowRepository.deleteById(id);
    }

    public Optional<DogShow> findByDogShowId(Long id) {
        return dogShowRepository.findById(id);
    }


}
