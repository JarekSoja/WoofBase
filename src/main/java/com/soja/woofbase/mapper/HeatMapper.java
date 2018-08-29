package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.Heat;
import com.soja.woofbase.domain.dto.inbound.HeatDtoIn;
import com.soja.woofbase.domain.dto.outbound.HeatDtoOut;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HeatMapper {

    public Heat mapToHeat(HeatDtoIn heatDtoIn) {
        return new Heat(
                heatDtoIn.getDogOwned(),
                heatDtoIn.getStartDate(),
                heatDtoIn.getEndDate()
        );
    }

    public HeatDtoOut mapToHeatDtoOut(Heat heat) {
        return new HeatDtoOut(
                heat.getDogOwned(),
                heat.getStartDate(),
                heat.getEndDate()
        );
    }

    public List<HeatDtoOut> mapToHeatDtoOutList(List<Heat> heats) {
        return heats.stream()
                .map(h -> mapToHeatDtoOut(h))
                .collect(Collectors.toList());
    }
}
