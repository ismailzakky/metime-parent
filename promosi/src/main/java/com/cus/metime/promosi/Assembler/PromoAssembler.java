package com.cus.metime.promosi.Assembler;

import com.cus.metime.promosi.Assembler.core.ObjectAssembler;
import com.cus.metime.promosi.domain.Promo;
import com.cus.metime.promosi.domain.builder.PromoBuilder;
import com.cus.metime.promosi.dto.PromoDTO;
import com.cus.metime.promosi.dto.builder.PromoDTOBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C-US on 9/26/2017.
 */
@Service
public class PromoAssembler implements ObjectAssembler<Promo,PromoDTO> {

    @Autowired
    private CreationalDateAssembler creationalDateAssembler;
    @Autowired
    private ValidityPeriodAssembler validityPeriodAssembler;

    @Override
    public Promo toDomain(PromoDTO dtoObject) {
        return new PromoBuilder()
            .setCreationalDate(creationalDateAssembler.toDomain(dtoObject.getCreationalDateDTO()))
            .setValidityPeriod(validityPeriodAssembler.toDomain(dtoObject.getValidityPeriodDTO()))
            .setSegment(dtoObject.getSegment())
            .setMediaFile(dtoObject.getMediaFile())
            .setPromoCategory(dtoObject.getPromoCategory())
            .setUuid(dtoObject.getUuid())
            .createPromo();
    }

    @Override
    public PromoDTO toDTO(Promo domainObject) {
        return new PromoDTOBuilder()
            .setCreationalDateDTO(creationalDateAssembler.toDTO(domainObject.getCreationalDate()))
            .setValidityPeriodDTO(validityPeriodAssembler.toDTO(domainObject.getValidityPeriod()))
            .setSegment(domainObject.getSegment())
            .setMediaFile(domainObject.getMediaFile())
            .setPromoCategory(domainObject.getPromoCategory())
            .setUuid(domainObject.getUuid())
            .createPromoDTO();
    }

    @Override
    public List<Promo> toDomainList(List<PromoDTO> dtoObjectList) {

        List<Promo> promoList = new ArrayList<>();
        Promo promo;
        for(PromoDTO promoDTO : dtoObjectList){
            promo = toDomain(promoDTO);
            promoList.add(promo);
        }
        return promoList;
    }

    @Override
    public List<PromoDTO> toDTOList(List<Promo> domainObjectList) {
        List<PromoDTO> promoList = new ArrayList<>();
        PromoDTO promo;
        for(Promo promoDTO : domainObjectList){
            promo = toDTO(promoDTO);
            promoList.add(promo);
        }
        return promoList;
    }
}
