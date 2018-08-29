package com.soja.woofbase.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "VET_VISITS")
public class VetVisit {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "VET_VISIT_ID", unique = true)
    private long vetVisitId;

    @ManyToOne
    @JoinColumn(name = "DOG_OWNED_VET_VISITS")
    private DogOwned dogOwned;

    @Column(name = "VET_VISIT_DESCRIPTIONS")
    private String vetVisitDescriptions;

    @Column (name = "VET_VISIT_EXCERPT")
    private String vetVisitExcerptURL;

    public VetVisit(DogOwned dogOwned, String vetVisitDescriptions, String vetVisitExcerptURL) {
        this.dogOwned = dogOwned;
        this.vetVisitDescriptions = vetVisitDescriptions;
        this.vetVisitExcerptURL = vetVisitExcerptURL;
    }

    public void setVetVisitId(long vetVisitId) {
        this.vetVisitId = vetVisitId;
    }

    public long getVetVisitId() {
        return vetVisitId;
    }

    public DogOwned getDogOwned() {
        return dogOwned;
    }

    public String getVetVisitDescriptions() {
        return vetVisitDescriptions;
    }

    public String getVetVisitExcerptURL() {
        return vetVisitExcerptURL;
    }
}
