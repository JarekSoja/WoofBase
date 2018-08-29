package com.soja.woofbase.domain.dto.outbound;

import com.soja.woofbase.domain.DogOwned;

import java.time.LocalDate;

public class DewormingDtoOut {

    private DogOwned dogOwned;
    private String medicationDtoOutUsed;
    private LocalDate dateOfApplicationDtoOut;
    private LocalDate dateOfNextApplicationDtoOut;
    private boolean isAlarmSet;


    public DewormingDtoOut(DogOwned dogOwned, String medicationDtoOutUsed, LocalDate dateOfApplicationDtoOut,
                           LocalDate dateOfNextApplicationDtoOut, boolean isAlarmSet) {
        this.dogOwned = dogOwned;
        this.medicationDtoOutUsed = medicationDtoOutUsed;
        this.dateOfApplicationDtoOut = dateOfApplicationDtoOut;
        this.dateOfNextApplicationDtoOut = dateOfNextApplicationDtoOut;
        this.isAlarmSet = isAlarmSet;
    }

    public DogOwned getDogOwned() {
        return dogOwned;
    }

    public String getMedicationDtoOutUsed() {
        return medicationDtoOutUsed;
    }

    public LocalDate getDateOfApplicationDtoOut() {
        return dateOfApplicationDtoOut;
    }

    public LocalDate getDateOfNextApplicationDtoOut() {
        return dateOfNextApplicationDtoOut;
    }

    public boolean isAlarmSet() {
        return isAlarmSet;
    }
}
