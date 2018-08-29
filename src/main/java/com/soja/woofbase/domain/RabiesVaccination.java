package com.soja.woofbase.domain;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Table(name = "RABIES_VACCINATIONS")
public class RabiesVaccination {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "RABIES_VACCINATION_ID", unique = true)
    private long rabiesVaccinationId;

    @ManyToOne
    @JoinColumn(name = "DOG_OWNED_RABIES_VACCINATIONS")
    private DogOwned dogOwned;

    @Column(name = "RABIES_VACCINATION_MEDICATION_USED")
    private String medicationUsed;

    @Past
    @Column (name = "DATE_OF_APPLICATION", updatable = false)
    private LocalDate dateOfApplication;

    @Future
    @Column (name = "DATE_OF_NEXT_APPLICATION", updatable = false)
    private LocalDate dateOfNextApplication;

    @Column(name = "ALARM_SET")
    private boolean isAlarmSet;

    public RabiesVaccination(DogOwned dogOwned, String medicationUsed, LocalDate dateOfApplication,
                             LocalDate dateOfNextApplication, boolean isAlarmSet) {
        this.dogOwned = dogOwned;
        this.medicationUsed = medicationUsed;
        this.dateOfApplication = dateOfApplication;
        this.dateOfNextApplication = dateOfNextApplication;
        this.isAlarmSet = isAlarmSet;
    }


    public boolean isAlarmSet() {
        return isAlarmSet;
    }

    public void setAlarm(boolean alarmSet) {
        isAlarmSet = alarmSet;
    }

    public void setRabiesVaccinationId(long rabiesVaccinationId) {
        this.rabiesVaccinationId = rabiesVaccinationId;
    }

    public long getRabiesVaccinationId() {
        return rabiesVaccinationId;
    }

    public DogOwned getDogOwned() {
        return dogOwned;
    }

    public LocalDate getDateOfApplication() {
        return dateOfApplication;
    }

    public LocalDate getDateOfNextApplication() {
        return dateOfNextApplication;
    }

    public String getMedicationUsed() {
        return medicationUsed;
    }
}
