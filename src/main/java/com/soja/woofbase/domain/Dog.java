package com.soja.woofbase.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@MappedSuperclass
public class Dog {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "DOG_ID", unique = true)
    private long dogId;

    @Column(name = "DOG_NAME", length = 100, nullable = false)
    private String dogName;
    @Column(name = "DOG_COLOUR", length = 100, nullable = false)
    private String dogColour;

    @Column(name = "DOG_SEX", nullable = false)
    private boolean dogSex;

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "DATE_OF_DEATH")
    private LocalDate dateOfDeath;

    @ManyToMany
    @JoinTable(
            name = "JOIN_PARENTS_PROGENY",
            joinColumns = @JoinColumn(name = "DOG_PROGENY"),
            inverseJoinColumns = @JoinColumn(name = "DOG_PARENT")
    )
    private List<Dog> dogParent;

    @ManyToMany(mappedBy = "dogParent")
    private List<Dog> dogProgeny;

    public Dog(String dogName, String dogColour, boolean dogSex, LocalDate dateOfBirth, LocalDate dateOfDeath, List<Dog> dogParent, List<Dog> dogProgeny) {
        this.dogName = dogName;
        this.dogColour = dogColour;
        this.dogSex = dogSex;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.dogParent = dogParent;
        this.dogProgeny = dogProgeny;
    }

    public Dog(String dogName, String dogColour, boolean dogSex, LocalDate dateOfBirth) {
        this.dogName = dogName;
        this.dogColour = dogColour;
        this.dogSex = dogSex;
        this.dateOfBirth = dateOfBirth;
    }

    public Dog() {

    }

    public void setDogId(long dogId) {
        this.dogId = dogId;
    }

    public long getDogId() {
        return dogId;
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
