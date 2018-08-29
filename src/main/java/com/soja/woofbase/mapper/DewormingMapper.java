package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.Deworming;
import com.soja.woofbase.domain.dto.inbound.DewormingDtoIn;
import com.soja.woofbase.domain.dto.outbound.DewormingDtoOut;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DewormingMapper {

    public Deworming mapToDeworming(DewormingDtoIn dewormingDtoIn) {
        return new Deworming(
                dewormingDtoIn.getDogOwned(),
                dewormingDtoIn.getMedicationDtoInUsed(),
                dewormingDtoIn.getDateOfApplicationDtoIn(),
                dewormingDtoIn.getDateOfNextApplicationDtoIn(),
                dewormingDtoIn.isAlarmSet()
        );
    }

    public DewormingDtoOut mapToDewormingDtoOut(Deworming deworming) {
        return new DewormingDtoOut(
                deworming.getDogOwned(),
                deworming.getMedicationUsed(),
                deworming.getDateOfApplication(),
                deworming.getDateOfNextApplication(),
                deworming.isAlarmSet()
        );
    }

    public List<DewormingDtoOut> mapToDewormingDtoOutList(List<Deworming> dewormings) {
        return dewormings.stream()
                .map(d -> mapToDewormingDtoOut(d))
                .collect(Collectors.toList());
    }
}
