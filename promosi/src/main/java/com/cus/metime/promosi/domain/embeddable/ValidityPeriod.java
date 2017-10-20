package com.cus.metime.promosi.domain.embeddable;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by C-US on 9/26/2017.
 */
public class ValidityPeriod  implements Serializable{

    private LocalDate startDate;
    private LocalDate endDate;

    public ValidityPeriod() {
    }

    public ValidityPeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof ValidityPeriod)) return false;

        ValidityPeriod that = (ValidityPeriod) o;

        if (getStartDate() != null ? !getStartDate().equals(that.getStartDate()) : that.getStartDate() != null)
            return false;
        return getEndDate() != null ? getEndDate().equals(that.getEndDate()) : that.getEndDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getStartDate() != null ? getStartDate().hashCode() : 0;
        result = 31 * result + (getEndDate() != null ? getEndDate().hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "ValidityPeriod{" +
            "startDate=" + startDate +
            ", endDate=" + endDate +
            '}';
    }
}
