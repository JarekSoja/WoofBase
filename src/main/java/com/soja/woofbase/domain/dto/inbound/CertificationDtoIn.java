package com.soja.woofbase.domain.dto.inbound;

import com.soja.woofbase.domain.DogOwned;

public class CertificationDtoIn {

    private DogOwned dogOwned;
    private String certificationDtoInName;
    private String certificationURL;


    public CertificationDtoIn(DogOwned dogOwned, String certificationDtoInName, String certificationURL) {
        this.dogOwned = dogOwned;
        this.certificationDtoInName = certificationDtoInName;
        this.certificationURL = certificationURL;
    }

    public DogOwned getDogOwned() {
        return dogOwned;
    }

    public void setDogOwned(DogOwned dogOwned) {
        this.dogOwned = dogOwned;
    }

    public String getCertificationDtoInName() {
        return certificationDtoInName;
    }

    public void setCertificationDtoInName(String certificationDtoInName) {
        this.certificationDtoInName = certificationDtoInName;
    }

    public String getCertificationURL() {
        return certificationURL;
    }

    public void setCertificationURL(String certificationURL) {
        this.certificationURL = certificationURL;
    }
}
