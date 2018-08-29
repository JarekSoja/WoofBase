package com.soja.woofbase.domain.dto.outbound;

import com.soja.woofbase.domain.Dog;
import com.soja.woofbase.domain.DogShow;

import java.time.LocalDate;
import java.util.List;

public abstract class DogDtoOut {

    private String dogName;
    private String dogColour;
    private boolean dogSex;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private List<Dog> dogParent;
    private List<Dog> dogProgeny;

    public DogDtoOut(String dogName, String dogColour, boolean dogSex, LocalDate dateOfBirth, LocalDate dateOfDeath,
                     List<Dog> dogParent, List<Dog> dogProgeny)
    {
        this.dogName = dogName;
        this.dogColour = dogColour;
        this.dogSex = dogSex;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.dogParent = dogParent;
        this.dogProgeny = dogProgeny;
    }

    public String getDogName() {
        return dogName;
    }

    public String getDogColour() {
        return dogColour;
    }

    public boolean getDogSex() {
        return dogSex;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }

    public List<Dog> getDogParent() {
        return dogParent;
    }

    public List<Dog> getDogProgeny() {
        return dogProgeny;
    }

}
