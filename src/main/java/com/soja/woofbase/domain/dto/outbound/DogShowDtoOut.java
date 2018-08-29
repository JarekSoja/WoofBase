package com.soja.woofbase.domain.dto.outbound;

import com.soja.woofbase.domain.Dog;
import com.soja.woofbase.domain.DogOwned;

public class DogShowDtoOut {

    private DogOwned dog;
    private String dogShowResultsURL;
    private String dogShowCatalogueURL;

    public DogShowDtoOut(DogOwned dog, String dogShowResultsURL, String dogShowCatalogueURL) {
        this.dog = dog;
        this.dogShowResultsURL = dogShowResultsURL;
        this.dogShowCatalogueURL = dogShowCatalogueURL;
    }

    public Dog getDogOwned() {
        return dog;
    }

    public String getDogShowResultsURL() {
        return dogShowResultsURL;
    }

    public String getDogShowCatalogueURL() {
        return dogShowCatalogueURL;
    }
}
