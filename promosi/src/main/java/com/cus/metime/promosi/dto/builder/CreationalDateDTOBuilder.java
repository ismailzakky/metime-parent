package com.cus.metime.promosi.dto.builder;

import com.cus.metime.promosi.dto.CreationalDateDTO;

import java.time.LocalDateTime;

public class CreationalDateDTOBuilder {
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    public CreationalDateDTOBuilder setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public CreationalDateDTOBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public CreationalDateDTOBuilder setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public CreationalDateDTOBuilder setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public CreationalDateDTO createCreationalDateDTO() {
        return new CreationalDateDTO(createdAt, createdBy, modifiedAt, modifiedBy);
    }
}
