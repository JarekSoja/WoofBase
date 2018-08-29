package com.soja.woofbase.domain;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Table(name = "SUPPLEMENTARY_VACCINATIONS")
public class SupplementaryVaccination {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "SUPPLEMENTARY_VACCINATIONS_ID", unique = true)
    private long supplementaryVaccinationId;

    @ManyToOne
    @JoinColumn(name = "DOG_OWNED_SUPPLEMENTARY_VACCINATIONS")
    private DogOwned dogOwned;

    @Column(name = "SUPPLEMENTARY_VACCINATIONS_MEDICATION_USED")
    private String medicationUsed;

    @Past
    @Column (name = "DATE_OF_APPLICATION", updatable = false)
    private LocalDate dateOfApplication;

    @Future
    @Column (name = "DATE_OF_NEXT_APPLICATION", updatable = false)
    private LocalDate dateOfNextApplication;

    @Column(name = "ALARM_SET")
    private boolean isAlarmSet;

    public SupplementaryVaccination(DogOwned dogOwned, String medicationUsed, LocalDate dateOfApplication,
                                    LocalDate dateOfNextApplication, boolean isAlarmSet) {
        this.dogOwned = dogOwned;
        this.medicationUsed = medicationUsed;
        this.dateOfApplication = dateOfApplication;
        this.dateOfNextApplication = dateOfNextApplication;
        this.isAlarmSet = isAlarmSet;
    }

    public void setSupplementaryVaccinationId(long supplementaryVaccinationId) {
        this.supplementaryVaccinationId = supplementaryVaccinationId;
    }



    public long getSupplementaryVaccinationId() {
        return supplementaryVaccinationId;
    }

    public DogOwned getDogOwned() {
        return dogOwned;
    }

    public String getMedicationUsed() {
        return medicationUsed;
    }

    public LocalDate getDateOfApplication() {
        return dateOfApplication;
    }

    public LocalDate getDateOfNextApplication() {
        return dateOfNextApplication;
    }

    public boolean isAlarmSet() {
        return isAlarmSet;
    }
}
