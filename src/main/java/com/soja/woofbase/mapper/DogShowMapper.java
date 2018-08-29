package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.DogShow;
import com.soja.woofbase.domain.dto.inbound.DogShowDtoIn;
import com.soja.woofbase.domain.dto.outbound.DogShowDtoOut;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DogShowMapper {

    public DogShow mapToDogShow(DogShowDtoIn dogShowDtoIn) {
        return new DogShow(
                dogShowDtoIn.getDogOwned(),
                dogShowDtoIn.getDogShowResultsURL(),
                dogShowDtoIn.getDogShowCatalogueURL()
        );
    }

    public DogShowDtoOut mapToDogShowDtoOut(DogShow dogShow) {
        return new DogShowDtoOut(
                dogShow.getDogOwned(),
                dogShow.getDogShowResultsURL(),
                dogShow.getDogShowCatalogueURL()
        );
    }

    public List<DogShowDtoOut> mapToDogShowDtoOutList(List<DogShow> dogShows) {
        return dogShows.stream()
                .map(d -> mapToDogShowDtoOut(d))
                .collect(Collectors.toList());
    }
}
