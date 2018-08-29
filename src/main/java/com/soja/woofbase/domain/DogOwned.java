package com.soja.woofbase.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "DOGS_OWNED")
public class DogOwned extends Dog {

    @Column(name = "DOG_OWNED_CHIP_NUMBER", unique = true)
    private long dogOwnedChipNumber;

    @ManyToOne
    @JoinColumn(name = "USER_DOGS_OWNED")
    private User user;

    @OneToMany(
            targetEntity = Deworming.class,
            mappedBy = "dogOwned",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Deworming>dewormings;

    @OneToMany(
            targetEntity = RabiesVaccination.class,
            mappedBy = "dogOwned",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<RabiesVaccination>rabiesVaccinations;

    @OneToMany(
            targetEntity = SupplementaryVaccination.class,
            mappedBy = "dogOwned",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<SupplementaryVaccination>supplementaryVaccinations;

    @OneToMany(
            targetEntity = TickPrevention.class,
            mappedBy = "dogOwned",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<TickPrevention>tickPreventions;

    @OneToMany(
            targetEntity = VetVisit.class,
            mappedBy = "dogOwned",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<VetVisit>vetVisits;

    @OneToMany(
            targetEntity = Heat.class,
            mappedBy = "dogOwned",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Heat>heats;

    @OneToMany(
            targetEntity = Certification.class,
            mappedBy = "dogOwned",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Certification>certifications;

    @OneToMany(
            targetEntity = DogShow.class,
            mappedBy = "dogOwned",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<DogShow> listOfDogShows;

    public DogOwned(User user, String dogName, String dogColour, boolean dogSex, LocalDate dateOfBirth, LocalDate dateOfDeath,
                    List<Dog> dogParent, List<Dog> dogProgeny, List<DogShow> listOfDogShows, long dogOwnedChipNumber,
                    List<Deworming> dewormings, List<RabiesVaccination> rabiesVaccinations, List<SupplementaryVaccination> supplementaryVaccinations,
                    List<TickPrevention> tickPreventions, List<VetVisit> vetVisits, List<Heat> heats, List<Certification> certifications)
    {
        super(dogName, dogColour, dogSex, dateOfBirth, dateOfDeath, dogParent, dogProgeny);
        this.user = user;
        this.listOfDogShows = listOfDogShows;
        this.dogOwnedChipNumber = dogOwnedChipNumber;
        this.dewormings = dewormings;
        this.rabiesVaccinations = rabiesVaccinations;
        this.supplementaryVaccinations = supplementaryVaccinations;
        this.tickPreventions = tickPreventions;
        this.vetVisits = vetVisits;
        this.heats = heats;
        this.certifications = certifications;
    }

    public DogOwned(User user, String dogName, String dogColour, boolean dogSex, LocalDate dateOfBirth) {
        super(dogName, dogColour, dogSex, dateOfBirth);
        this.user = user;
    }

    public DogOwned() {

    }

    public User getUser() {
        return user;
    }

    public long getDogOwnedChipNumber() {
        return dogOwnedChipNumber;
    }

    public List<Deworming> getDewormings() {
        return dewormings;
    }

    public List<RabiesVaccination> getRabiesVaccinations() {
        return rabiesVaccinations;
    }

    public List<SupplementaryVaccination> getSupplementaryVaccinations() {
        return supplementaryVaccinations;
    }

    public List<TickPrevention> getTickPreventions() {
        return tickPreventions;
    }

    public List<VetVisit> getVetVisits() {
        return vetVisits;
    }

    public List<Heat> getHeats() {
        return heats;
    }

    public List<Certification> getCertifications() {
        return certifications;
    }

    public List<DogShow> getListOfDogShows() {
        return listOfDogShows;
    }
}
