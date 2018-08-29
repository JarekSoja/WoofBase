package com.soja.woofbase.domain.dto.inbound;

import com.soja.woofbase.domain.DogOwned;

public class DogShowDtoIn {

    private DogOwned dogOwned;
    private String dogShowResultsURL;
    private String dogShowCatalogueURL;

    public DogShowDtoIn(DogOwned dogOwned, String dogShowResultsURL, String dogShowCatalogueURL) {
        this.dogOwned = dogOwned;
        this.dogShowResultsURL = dogShowResultsURL;
        this.dogShowCatalogueURL = dogShowCatalogueURL;
    }

    public DogOwned getDogOwned() {
        return dogOwned;
    }

    public String getDogShowResultsURL() {
        return dogShowResultsURL;
    }

    public String getDogShowCatalogueURL() {
        return dogShowCatalogueURL;
    }
}
