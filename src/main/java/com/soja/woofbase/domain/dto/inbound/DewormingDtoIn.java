package com.soja.woofbase.domain.dto.inbound;

import com.soja.woofbase.domain.DogOwned;

import java.time.LocalDate;

public class DewormingDtoIn {

    private DogOwned dogOwned;
    private String medicationDtoInUsed;
    private LocalDate dateOfApplicationDtoIn;
    private LocalDate dateOfNextApplicationDtoIn;
    private boolean isAlarmSet;


    public DewormingDtoIn(DogOwned dogOwned, String medicationDtoInUsed, LocalDate dateOfApplicationDtoIn,
                          LocalDate dateOfNextApplicationDtoIn, boolean isAlarmSet) {
        this.dogOwned = dogOwned;
        this.medicationDtoInUsed = medicationDtoInUsed;
        this.dateOfApplicationDtoIn = dateOfApplicationDtoIn;
        this.dateOfNextApplicationDtoIn = dateOfNextApplicationDtoIn;
        this.isAlarmSet = isAlarmSet;
    }

    public DogOwned getDogOwned() {
        return dogOwned;
    }

    public String getMedicationDtoInUsed() {
        return medicationDtoInUsed;
    }

    public LocalDate getDateOfApplicationDtoIn() {
        return dateOfApplicationDtoIn;
    }

    public LocalDate getDateOfNextApplicationDtoIn() {
        return dateOfNextApplicationDtoIn;
    }

    public boolean isAlarmSet() {
        return isAlarmSet;
    }
}
