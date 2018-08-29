package com.soja.woofbase.domain.dto.outbound;

import com.soja.woofbase.domain.DogOwned;
public class VetVisitDtoOut {

    private DogOwned dogOwned;
    private String vetVisitDescriptions;
    private String vetVisitExcerptURL;

    public VetVisitDtoOut(DogOwned dogOwned, String vetVisitDescriptions, String vetVisitExcerptURL) {
        this.dogOwned = dogOwned;
        this.vetVisitDescriptions = vetVisitDescriptions;
        this.vetVisitExcerptURL = vetVisitExcerptURL;
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
