package com.soja.woofbase.domain.dto.outbound;

import com.soja.woofbase.domain.DogOwned;

public class CertificationDtoOut {

    private Long id;
    private DogOwned dogOwned;
    private String certificationDtoOutName;
    private String certificationDtoOutURL;

    public CertificationDtoOut(DogOwned dogOwned, String certificationDtoOutName, String certificationURL) {
        this.dogOwned = dogOwned;
        this.certificationDtoOutName = certificationDtoOutName;
        this.certificationDtoOutURL = certificationURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

       public DogOwned getDogOwned() {
        return dogOwned;
    }

    public void setDogOwned(DogOwned dogOwned) {
        this.dogOwned = dogOwned;
    }

    public String getCertificationDtoOutName() {
        return certificationDtoOutName;
    }

    public void setCertificationDtoOutName(String certificationDtoOutName) {
        this.certificationDtoOutName = certificationDtoOutName;
    }

    public String getCertificationDtoOutURL() {
        return certificationDtoOutURL;
    }

    public void setCertificationDtoOutURL(String certificationURL) {
        this.certificationDtoOutURL = certificationURL;
    }
}
