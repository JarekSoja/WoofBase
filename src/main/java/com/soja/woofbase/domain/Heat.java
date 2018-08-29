package com.soja.woofbase.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Table(name = "HEATS")
public class Heat {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "HEAT_ID", unique = true)
    private long heatId;

    @ManyToOne
    @JoinColumn(name = "DOG_OWNED_HEATS")
    private DogOwned dogOwned;

    @Past
    @Column(name = "START_DATE", nullable = false)
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    public Heat(DogOwned dogOwned, LocalDate startDate, LocalDate endDate) {
        this.dogOwned = dogOwned;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getHeatId() {
        return heatId;
    }

    public void setHeatId(long heatId) {
        this.heatId = heatId;
    }

    public DogOwned getDogOwned() {
        return dogOwned;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
