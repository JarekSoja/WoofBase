package com.soja.woofbase.domain;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Table(name = "DEWORMINGS")

public class Deworming {


    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "DEWORMING_ID", unique = true)
    private long dewormingId;

    @ManyToOne
    @JoinColumn(name = "DOG_OWNED_DEWORMINGS")
    private DogOwned dogOwned;

    @Column(name = "DEWORMING_MEDICATION_USED")
    private String medicationUsed;

    @Past
    @Column (name = "DATE_OF_APPLICATION", updatable = false)
    private LocalDate dateOfApplication;

    @Future
    @Column (name = "DATE_OF_NEXT_APPLICATION", updatable = false)
    private LocalDate dateOfNextApplication;

    @Column(name = "ALARM_SET")
    private boolean isAlarmSet;

    public Deworming(DogOwned dogOwned, String medicationUsed, LocalDate dateOfApplication,
                     LocalDate dateOfNextApplication, boolean isAlarmSet) {
        this.dogOwned = dogOwned;
        this.medicationUsed = medicationUsed;
        this.dateOfApplication = dateOfApplication;
        this.dateOfNextApplication = dateOfNextApplication;
        this.isAlarmSet = isAlarmSet;
    }

    public void setDewormingId(long dewormingId) {
        this.dewormingId = dewormingId;
    }

    public boolean isAlarmSet() {
        return isAlarmSet;
    }

    public void setAlarm(boolean alarmSet) {
        isAlarmSet = alarmSet;
    }

    public long getDewormingId() {
        return dewormingId;
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
}
