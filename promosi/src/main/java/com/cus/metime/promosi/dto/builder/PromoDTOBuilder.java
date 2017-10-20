package com.cus.metime.promosi.dto.builder;

import com.cus.metime.promosi.domain.enumeration.PromoCategory;
import com.cus.metime.promosi.dto.CreationalDateDTO;
import com.cus.metime.promosi.dto.PromoDTO;
import com.cus.metime.promosi.dto.ValidityPeriodDTO;

public class PromoDTOBuilder {
    private Long id;
    private PromoCategory promoCategory;
    private String mediaFile;
    private ValidityPeriodDTO validityPeriodDTO;
    private CreationalDateDTO creationalDateDTO;
    private String segment;
    private String uuid;

    public PromoDTOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public PromoDTOBuilder setPromoCategory(PromoCategory promoCategory) {
        this.promoCategory = promoCategory;
        return this;
    }

    public PromoDTOBuilder setMediaFile(String mediaFile) {
        this.mediaFile = mediaFile;
        return this;
    }

    public PromoDTOBuilder setValidityPeriodDTO(ValidityPeriodDTO validityPeriodDTO) {
        this.validityPeriodDTO = validityPeriodDTO;
        return this;
    }

    public PromoDTOBuilder setCreationalDateDTO(CreationalDateDTO creationalDateDTO) {
        this.creationalDateDTO = creationalDateDTO;
        return this;
    }

    public PromoDTOBuilder setSegment(String segment) {
        this.segment = segment;
        return this;
    }

    public PromoDTOBuilder setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public PromoDTO createPromoDTO() {
        return new PromoDTO(id, promoCategory, mediaFile, validityPeriodDTO, creationalDateDTO, segment, uuid);
    }
}
