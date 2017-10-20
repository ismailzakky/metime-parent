package com.cus.metime.promosi.domain.embeddable.builder;

import com.cus.metime.promosi.domain.embeddable.ValidityPeriod;

import java.time.LocalDate;

public class ValidityPeriodBuilder {
    private LocalDate startDate;
    private LocalDate endDate;

    public ValidityPeriodBuilder setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public ValidityPeriodBuilder setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public ValidityPeriod createValidityPeriod() {
        return new ValidityPeriod(startDate, endDate);
    }
}
