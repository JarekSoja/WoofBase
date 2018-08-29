package com.soja.woofbase.service;

import com.soja.woofbase.validate.DogNotFoundException;
import com.soja.woofbase.domain.Dog;
import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.repository.DogOwnedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DogOwnedService {

    private final DogOwnedRepository dogOwnedRepository;

    @Autowired
    public DogOwnedService(DogOwnedRepository dogOwnedRepository) {
        this.dogOwnedRepository = dogOwnedRepository;
    }

    public Optional<DogOwned> getDogOwnedById(Long id) {
        return dogOwnedRepository.findById(id);
    }

    public List<Dog> getDogsByParent(Dog dog) throws DogNotFoundException {
        List<Dog> searchedDogs = new ArrayList<>();
        searchedDogs.addAll(dogOwnedRepository.getAllByDogParent(dog));

        if (searchedDogs.isEmpty()) {
            throw new DogNotFoundException();
        }
        return searchedDogs;
    }


    public Iterable<DogOwned> getAllDogsOwned() {
        return dogOwnedRepository.findAll();
    }

    public DogOwned save(DogOwned dogOwned) {
        return dogOwnedRepository.save(dogOwned);
    }

    public void deleteById(Long id) {
        dogOwnedRepository.deleteById(id);
    }

}
