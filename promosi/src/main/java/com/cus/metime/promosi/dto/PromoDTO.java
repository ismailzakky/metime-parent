package com.cus.metime.promosi.dto;

import com.cus.metime.promosi.domain.enumeration.PromoCategory;

import java.io.Serializable;

/**
 * Created by C-US on 9/26/2017.
 */
public class PromoDTO implements Serializable{

    private Long id;
    private PromoCategory promoCategory;
    private String mediaFile;
    private ValidityPeriodDTO validityPeriodDTO;
    private CreationalDateDTO creationalDateDTO;
    private String segment;
    private String uuid;

    public PromoDTO() {

    }

    public PromoDTO(Long id, PromoCategory promoCategory, String mediaFile, ValidityPeriodDTO validityPeriodDTO, CreationalDateDTO creationalDateDTO, String segment, String uuid) {
        this.id = id;
        this.promoCategory = promoCategory;
        this.mediaFile = mediaFile;
        this.validityPeriodDTO = validityPeriodDTO;
        this.creationalDateDTO = creationalDateDTO;
        this.segment = segment;
        this.uuid = uuid;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PromoCategory getPromoCategory() {
        return promoCategory;
    }

    public void setPromoCategory(PromoCategory promoCategory) {
        this.promoCategory = promoCategory;
    }

    public String getMediaFile() {
        return mediaFile;
    }

    public void setMediaFile(String mediaFile) {
        this.mediaFile = mediaFile;
    }

    public ValidityPeriodDTO getValidityPeriodDTO() {
        return validityPeriodDTO;
    }

    public void setValidityPeriodDTO(ValidityPeriodDTO validityPeriodDTO) {
        this.validityPeriodDTO = validityPeriodDTO;
    }

    public CreationalDateDTO getCreationalDateDTO() {
        return creationalDateDTO;
    }

    public void setCreationalDateDTO(CreationalDateDTO creationalDateDTO) {
        this.creationalDateDTO = creationalDateDTO;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "PromoDTO{" +
            "id=" + id +
            ", promoCategory=" + promoCategory +
            ", mediaFile='" + mediaFile + '\'' +
            ", validityPeriodDTO=" + validityPeriodDTO +
            ", creationalDateDTO=" + creationalDateDTO +
            ", segment='" + segment + '\'' +
            ", uuid='" + uuid + '\'' +
            '}';
    }
}
