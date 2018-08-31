package com.soja.woofbase.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "DOGS_WATCHED")
public class DogWatched extends Dog {

    @Column(name = "REASON_TO_WATCH")
    private String reasonToWatch;

    public DogWatched(String reasonToWatch) {
        this.reasonToWatch = reasonToWatch;
    }

    public DogWatched(String dogName, String dogColour, boolean dogSex, LocalDate dateOfBirth, String reasonToWatch) {
        super(dogName, dogColour, dogSex, dateOfBirth);
        this.reasonToWatch = reasonToWatch;
    }

    public DogWatched() {
    }

    public String getReasonToWatch() {
        return reasonToWatch;
    }

    public void setReasonToWatch(String reasonToWatch) {
        this.reasonToWatch = reasonToWatch;
    }
}
