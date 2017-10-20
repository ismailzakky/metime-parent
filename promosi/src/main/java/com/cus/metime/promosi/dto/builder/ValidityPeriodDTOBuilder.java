package com.cus.metime.promosi.dto.builder;

import com.cus.metime.promosi.dto.ValidityPeriodDTO;

import java.time.LocalDate;

public class ValidityPeriodDTOBuilder {
    private LocalDate startDate;
    private LocalDate endDate;

    public ValidityPeriodDTOBuilder setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public ValidityPeriodDTOBuilder setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public ValidityPeriodDTO createValidityPeriodDTO() {
        return new ValidityPeriodDTO(startDate, endDate);
    }
}
