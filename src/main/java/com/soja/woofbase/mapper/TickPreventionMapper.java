package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.TickPrevention;
import com.soja.woofbase.domain.dto.inbound.TickPreventionDtoIn;
import com.soja.woofbase.domain.dto.outbound.TickPreventionDtoOut;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TickPreventionMapper {

    public TickPrevention mapToTickPrevention(TickPreventionDtoIn tickPreventionDtoIn) {
        return new TickPrevention(
                tickPreventionDtoIn.getDogOwned(),
                tickPreventionDtoIn.getMedicationUsed(),
                tickPreventionDtoIn.getDateOfApplication(),
                tickPreventionDtoIn.getDateOfNextApplication(),
                tickPreventionDtoIn.isAlarmSet()
        );
    }

    public TickPreventionDtoOut mapToTickPreventionDtoOut(TickPrevention tickPrevention) {
        return new TickPreventionDtoOut(
                tickPrevention.getDogOwned(),
                tickPrevention.getMedicationUsed(),
                tickPrevention.getDateOfApplication(),
                tickPrevention.getDateOfNextApplication(),
                tickPrevention.isAlarmSet()
        );
    }

    public List<TickPreventionDtoOut> mapToTickPreventionDtoOutList(List<TickPrevention> preventions) {
        return preventions.stream()
                .map(t -> mapToTickPreventionDtoOut(t))
                .collect(Collectors.toList());
    }
}
