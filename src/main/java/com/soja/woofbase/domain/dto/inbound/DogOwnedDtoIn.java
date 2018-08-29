package com.soja.woofbase.domain.dto.inbound;

import com.soja.woofbase.domain.*;

import java.time.LocalDate;
import java.util.List;

public class DogOwnedDtoIn extends DogDtoIn {

    private User user;
    private long dogOwnedChipNumber;
    private List<Deworming>dewormings;
    private List<RabiesVaccination>rabiesVaccinations;
    private List<SupplementaryVaccination>supplementaryVaccinations;
    private List<TickPrevention>tickPreventions;
    private List<VetVisit>vetVisits;
    private List<Heat>heats;
    private List<Certification>certifications;
    private List<DogShow> listOfDogShows;

    public DogOwnedDtoIn(User user, String dogName, String dogColour, boolean dogSex, LocalDate dateOfBirth, LocalDate dateOfDeath,
                         List<Dog> dogParent, List<Dog> dogProgeny, List<DogShow> listOfDogShows, long dogOwnedChipNumber,
                         List<Deworming> dewormings, List<RabiesVaccination> rabiesVaccinations,
                         List<SupplementaryVaccination> supplementaryVaccinations, List<TickPrevention> tickPreventions,
                         List<VetVisit> vetVisits, List<Heat> heats, List<Certification> certifications)
    {
        super(dogName, dogColour, dogSex, dateOfBirth, dateOfDeath, dogParent, dogProgeny);
        this.user = user;
        this.dogOwnedChipNumber = dogOwnedChipNumber;
        this.dewormings = dewormings;
        this.rabiesVaccinations = rabiesVaccinations;
        this.supplementaryVaccinations = supplementaryVaccinations;
        this.tickPreventions = tickPreventions;
        this.vetVisits = vetVisits;
        this.heats = heats;
        this.certifications = certifications;
        this.listOfDogShows = listOfDogShows;
    }

    public DogOwnedDtoIn(String dogName, String dogColour, boolean dogSex, LocalDate dateOfBirth) {
        super(dogName, dogColour, dogSex, dateOfBirth);
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

    public User getUser() {
        return user;
    }
}
