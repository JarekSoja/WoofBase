package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.VetVisit;
import com.soja.woofbase.domain.dto.inbound.VetVisitDtoIn;
import com.soja.woofbase.domain.dto.outbound.VetVisitDtoOut;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VetVisitMapper {

    public VetVisit mapToVetVisit(VetVisitDtoIn vetVisitDtoIn) {
        return new VetVisit(
                vetVisitDtoIn.getDogOwned(),
                vetVisitDtoIn.getVetVisitDescriptions(),
                vetVisitDtoIn.getVetVisitExcerptURL()
        );
    }

    public VetVisitDtoOut mapToVetVisitDtoOut(VetVisit vetVisit) {
        return new VetVisitDtoOut(
                vetVisit.getDogOwned(),
                vetVisit.getVetVisitDescriptions(),
                vetVisit.getVetVisitExcerptURL()
        );
    }

    public List<VetVisitDtoOut> mapToVetVisitDtoOutList(List<VetVisit> visits) {
        return visits.stream()
                .map(v -> mapToVetVisitDtoOut(v))
                .collect(Collectors.toList());
    }
}
