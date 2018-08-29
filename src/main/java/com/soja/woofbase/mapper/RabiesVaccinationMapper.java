package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.RabiesVaccination;
import com.soja.woofbase.domain.dto.inbound.RabiesVaccinationDtoIn;
import com.soja.woofbase.domain.dto.outbound.RabiesVaccinationDtoOut;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RabiesVaccinationMapper {

    public RabiesVaccination mapToRabiesVaccination(RabiesVaccinationDtoIn rabiesVaccinationDtoIn) {
        return new RabiesVaccination(
                rabiesVaccinationDtoIn.getDogOwned(),
                rabiesVaccinationDtoIn.getMedicationUsed(),
                rabiesVaccinationDtoIn.getDateOfApplication(),
                rabiesVaccinationDtoIn.getDateOfNextApplication(),
                rabiesVaccinationDtoIn.isAlarmSet()
        );
    }

    public RabiesVaccinationDtoOut mapToRabiesVaccinationDtoOut(RabiesVaccination rabiesVaccination) {
        return new RabiesVaccinationDtoOut(
                rabiesVaccination.getDogOwned(),
                rabiesVaccination.getMedicationUsed(),
                rabiesVaccination.getDateOfApplication(),
                rabiesVaccination.getDateOfNextApplication(),
                rabiesVaccination.isAlarmSet()
        );
    }

    public List<RabiesVaccinationDtoOut> mapToRabiesVaccinationDtoOutList(List<RabiesVaccination> rabiesVaccinations) {
        return rabiesVaccinations.stream()
                .map(r -> mapToRabiesVaccinationDtoOut(r))
                .collect(Collectors.toList());
    }
}
