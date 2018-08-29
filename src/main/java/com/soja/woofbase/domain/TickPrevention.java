package com.soja.woofbase.domain;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Table(name = "TICK_PREVENTION")
public class TickPrevention {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "TICK_PREVENTION_ID", unique = true)
    private long tickPreventionId;

    @ManyToOne
    @JoinColumn(name = "DOG_OWNED_TICK_PREVENTION")
    private DogOwned dogOwned;

    @Column(name = "TICK_PREVENTION_MEDICATION_USED")
    private String medicationUsed;

    @Past
    @Column (name = "DATE_OF_APPLICATION", updatable = false)
    private LocalDate dateOfApplication;

    @Future
    @Column (name = "DATE_OF_NEXT_APPLICATION", updatable = false)
    private LocalDate dateOfNextApplication;

    @Column(name = "ALARM_SET")
    private boolean isAlarmSet;

    public TickPrevention(DogOwned dogOwned, String medicationUsed, LocalDate dateOfApplication,
                          LocalDate dateOfNextApplication, boolean isAlarmSet) {
        this.dogOwned = dogOwned;
        this.medicationUsed = medicationUsed;
        this.dateOfApplication = dateOfApplication;
        this.dateOfNextApplication = dateOfNextApplication;
        this.isAlarmSet = isAlarmSet;
    }

    public void setTickPreventionId(long tickPreventionId) {
        this.tickPreventionId = tickPreventionId;
    }

    public long getTickPreventionId() {
        return tickPreventionId;
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
