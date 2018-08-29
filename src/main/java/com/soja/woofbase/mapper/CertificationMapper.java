package com.soja.woofbase.mapper;

import com.soja.woofbase.domain.Certification;
import com.soja.woofbase.domain.dto.inbound.CertificationDtoIn;
import com.soja.woofbase.domain.dto.outbound.CertificationDtoOut;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CertificationMapper {

    public Certification mapToCertification(CertificationDtoIn certificationDtoIn) {
        return new Certification(
                certificationDtoIn.getDogOwned(),
                certificationDtoIn.getCertificationDtoInName(),
                certificationDtoIn.getCertificationURL()
        );
    }

    public CertificationDtoOut mapToCertificationDtoOut(Certification certification) {
        return new CertificationDtoOut(
                certification.getDogOwned(),
                certification.getCertificationName(),
                certification.getCertificationURL()
        );
    }

    public List<CertificationDtoOut> mapToCertificationDtoOutList(List<Certification> certifications) {
        return certifications.stream()
                .map(c -> mapToCertificationDtoOut(c))
                .collect(Collectors.toList());
    }

}
