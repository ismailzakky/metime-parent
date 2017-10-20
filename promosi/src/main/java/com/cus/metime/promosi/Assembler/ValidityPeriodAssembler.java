package com.cus.metime.promosi.Assembler;

import com.cus.metime.promosi.Assembler.core.ObjectAssembler;
import com.cus.metime.promosi.domain.embeddable.ValidityPeriod;
import com.cus.metime.promosi.domain.embeddable.builder.ValidityPeriodBuilder;
import com.cus.metime.promosi.dto.ValidityPeriodDTO;
import com.cus.metime.promosi.dto.builder.ValidityPeriodDTOBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by C-US on 9/26/2017.
 */
@Service
public class ValidityPeriodAssembler implements ObjectAssembler<ValidityPeriod,ValidityPeriodDTO> {


    @Override
    public ValidityPeriod toDomain(ValidityPeriodDTO dtoObject) {

        return new ValidityPeriodBuilder()
            .setEndDate(dtoObject.getEndDate())
            .setStartDate(dtoObject.getStartDate())
            .createValidityPeriod();
    }

    @Override
    public ValidityPeriodDTO toDTO(ValidityPeriod domainObject) {

        return new ValidityPeriodDTOBuilder()
            .setEndDate(domainObject.getEndDate())
            .setStartDate(domainObject.getStartDate())
            .createValidityPeriodDTO();
    }

    @Override
    public List<ValidityPeriod> toDomainList(List<ValidityPeriodDTO> dtoObjectList) {
        return null;
    }

    @Override
    public List<ValidityPeriodDTO> toDTOList(List<ValidityPeriod> domainObjectList) {
        return null;
    }
}
