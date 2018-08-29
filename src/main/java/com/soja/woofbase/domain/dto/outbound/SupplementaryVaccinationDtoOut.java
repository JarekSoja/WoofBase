package com.soja.woofbase.domain.dto.outbound;

import com.soja.woofbase.domain.DogOwned;
import java.time.LocalDate;

public class SupplementaryVaccinationDtoOut {

    private DogOwned dogOwned;
    private String medicationUsed;
    private LocalDate dateOfApplication;
    private LocalDate dateOfNextApplication;
    private boolean isAlarmSet;


    public SupplementaryVaccinationDtoOut(DogOwned dogOwned, String medicationUsed, LocalDate dateOfApplication,
                                          LocalDate dateOfNextApplication, boolean isAlarmSet) {
        this.dogOwned = dogOwned;
        this.medicationUsed = medicationUsed;
        this.dateOfApplication = dateOfApplication;
        this.dateOfNextApplication = dateOfNextApplication;
        this.isAlarmSet = isAlarmSet;
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
