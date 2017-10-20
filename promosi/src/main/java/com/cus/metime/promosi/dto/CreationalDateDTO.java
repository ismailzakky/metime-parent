package com.cus.metime.promosi.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by C-US on 9/26/2017.
 */
public class CreationalDateDTO implements Serializable{

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    public CreationalDateDTO() {
    }

    public CreationalDateDTO(LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public String toString() {
        return "CreationalDateDTO{" +
            "createdAt=" + createdAt +
            ", createdBy='" + createdBy + '\'' +
            ", modifiedAt=" + modifiedAt +
            ", modifiedBy='" + modifiedBy + '\'' +
            '}';
    }
}
