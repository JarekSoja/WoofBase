package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.SupplementaryVaccination;
import com.soja.woofbase.domain.dto.inbound.SupplementaryVaccinationDtoIn;
import com.soja.woofbase.domain.dto.outbound.SupplementaryVaccinationDtoOut;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupplementaryVaccinationMapper {

    public SupplementaryVaccination mapToSupplementaryVaccination(SupplementaryVaccinationDtoIn supplementaryVaccinationDtoIn) {
        return new SupplementaryVaccination(
                supplementaryVaccinationDtoIn.getDogOwned(),
                supplementaryVaccinationDtoIn.getMedicationUsed(),
                supplementaryVaccinationDtoIn.getDateOfApplication(),
                supplementaryVaccinationDtoIn.getDateOfNextApplication(),
                supplementaryVaccinationDtoIn.isAlarmSet()
        );
    }

    public SupplementaryVaccinationDtoOut mapToSupplementaryVaccinationDtoOut(SupplementaryVaccination supplementaryVaccination) {
        return new SupplementaryVaccinationDtoOut(
                supplementaryVaccination.getDogOwned(),
                supplementaryVaccination.getMedicationUsed(),
                supplementaryVaccination.getDateOfApplication(),
                supplementaryVaccination.getDateOfNextApplication(),
                supplementaryVaccination.isAlarmSet()
        );
    }

    public List<SupplementaryVaccinationDtoOut> mapToSupplementaryVaccinationDtoOutList(List<SupplementaryVaccination> vaccinations) {
        return vaccinations.stream()
                .map(v -> mapToSupplementaryVaccinationDtoOut(v))
                .collect(Collectors.toList());
    }
}
