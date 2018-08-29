package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.dto.inbound.DogOwnedDtoIn;
import com.soja.woofbase.domain.dto.outbound.DogOwnedDtoOut;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DogOwnedMapper {

    public DogOwned mapToDogOwned(DogOwnedDtoIn dogOwnedDtoIn) {
        return new DogOwned(
                dogOwnedDtoIn.getUser(),
                dogOwnedDtoIn.getDogName(),
                dogOwnedDtoIn.getDogColour(),
                dogOwnedDtoIn.getDogSex(),
                dogOwnedDtoIn.getDateOfBirth(),
                dogOwnedDtoIn.getDateOfDeath(),
                dogOwnedDtoIn.getDogParent(),
                dogOwnedDtoIn.getDogProgeny(),
                dogOwnedDtoIn.getListOfDogShows(),
                dogOwnedDtoIn.getDogOwnedChipNumber(),
                dogOwnedDtoIn.getDewormings(),
                dogOwnedDtoIn.getRabiesVaccinations(),
                dogOwnedDtoIn.getSupplementaryVaccinations(),
                dogOwnedDtoIn.getTickPreventions(),
                dogOwnedDtoIn.getVetVisits(),
                dogOwnedDtoIn.getHeats(),
                dogOwnedDtoIn.getCertifications()
        );
    }

    //TODO mappers with different number of arguments

    public DogOwnedDtoOut mapToDogOwnedDtoOut(DogOwned dogOwned) {
        return new DogOwnedDtoOut(
                dogOwned.getUser(),
                dogOwned.getDogName(),
                dogOwned.getDogColour(),
                dogOwned.getDogSex(),
                dogOwned.getDateOfBirth(),
                dogOwned.getDateOfDeath(),
                dogOwned.getDogParent(),
                dogOwned.getDogProgeny(),
                dogOwned.getListOfDogShows(),
                dogOwned.getDogOwnedChipNumber(),
                dogOwned.getDewormings(),
                dogOwned.getRabiesVaccinations(),
                dogOwned.getSupplementaryVaccinations(),
                dogOwned.getTickPreventions(),
                dogOwned.getVetVisits(),
                dogOwned.getHeats(),
                dogOwned.getCertifications()
        );
    }

    public List<DogOwnedDtoOut> mapToDogOwnedDtoOutList(List<DogOwned> dogsOwned) {
        return dogsOwned.stream()
                .map(d -> mapToDogOwnedDtoOut(d))
                .collect(Collectors.toList());
    }

}
