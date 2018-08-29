package com.soja.woofbase.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "CERTIFICATIONS")
public class Certification {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "CERTIFICATION_ID", unique = true)
    private long certificationId;

    @ManyToOne
    @JoinColumn(name = "DOG_OWNED_CERTIFICATIONS")
    private DogOwned dogOwned;

    @Column(name = "CERTIFICATION_NAME")
    private String certificationName;

    @Column(name = "CERTIFICATION_FILE")
    private String certificationURL;

    public Certification(DogOwned dogOwned, String certificationName, String certificationURL) {
        this.dogOwned = dogOwned;
        this.certificationName = certificationName;
        this.certificationURL = certificationURL;
    }

    public void setCertificationId(long certificationId) {
        this.certificationId = certificationId;
    }

    public long getCertificationId() {
        return certificationId;
    }

    public DogOwned getDogOwned() {
        return dogOwned;
    }

    public String getCertificationName() {
        return certificationName;
    }

    public String getCertificationURL() {
        return certificationURL;
    }

    public void setDogOwned(DogOwned dogOwned) {
        this.dogOwned = dogOwned;
    }

    public void setCertificationName(String certificationName) {
        this.certificationName = certificationName;
    }

    public void setCertificationURL(String certificationURL) {
        this.certificationURL = certificationURL;
    }
}
