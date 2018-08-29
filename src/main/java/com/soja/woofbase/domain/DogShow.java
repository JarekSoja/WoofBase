package com.soja.woofbase.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "DOG_SHOWS")
public class DogShow {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "DOG_SHOW_ID", unique = true)
    private long dogShowId;

    @ManyToOne
    @JoinColumn(name = "DOG_SHOWS")
    private DogOwned dogOwned;

    @Column(name = "DOG_SHOW_RESULTS")
    private String dogShowResultsURL;

    @Column(name = "DOG_SHOW_CATALOGUE")
    private String dogShowCatalogueURL;

    public DogShow(DogOwned dog, String dogShowResultsURL, String dogShowCatalogueURL) {
        this.dogOwned = dog;
        this.dogShowResultsURL = dogShowResultsURL;
        this.dogShowCatalogueURL = dogShowCatalogueURL;
    }

    public void setDogShowId(long dogShowId) {
        this.dogShowId = dogShowId;
    }

    public long getDogShowId() {
        return dogShowId;
    }

    public String getDogShowResultsURL() {
        return dogShowResultsURL;
    }

    public String getDogShowCatalogueURL() {
        return dogShowCatalogueURL;
    }

    public DogOwned getDogOwned() {
        return dogOwned;
    }

}
