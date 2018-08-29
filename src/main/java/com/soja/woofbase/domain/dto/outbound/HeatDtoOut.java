package com.soja.woofbase.domain.dto.outbound;

import com.soja.woofbase.domain.DogOwned;

import java.time.LocalDate;

public class HeatDtoOut {

    private DogOwned dogOwned;
    private LocalDate startDate;
    private LocalDate endDate;

    public HeatDtoOut(DogOwned dogOwned, LocalDate startDate, LocalDate endDate) {
        this.dogOwned = dogOwned;
        this.startDate = startDate;
        this.endDate = endDate;
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
