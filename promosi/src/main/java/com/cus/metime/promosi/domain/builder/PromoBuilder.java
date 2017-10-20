package com.cus.metime.promosi.domain.builder;

import com.cus.metime.promosi.domain.Promo;
import com.cus.metime.promosi.domain.embeddable.CreationalDate;
import com.cus.metime.promosi.domain.embeddable.ValidityPeriod;
import com.cus.metime.promosi.domain.enumeration.PromoCategory;

public class PromoBuilder {
    private String mediaFile;
    private PromoCategory promoCategory;
    private CreationalDate creationalDate;
    private ValidityPeriod validityPeriod;
    private String segment;
    private String uuid;

    public PromoBuilder setMediaFile(String mediaFile) {
        this.mediaFile = mediaFile;
        return this;
    }

    public PromoBuilder setPromoCategory(PromoCategory promoCategory) {
        this.promoCategory = promoCategory;
        return this;
    }

    public PromoBuilder setCreationalDate(CreationalDate creationalDate) {
        this.creationalDate = creationalDate;
        return this;
    }

    public PromoBuilder setValidityPeriod(ValidityPeriod validityPeriod) {
        this.validityPeriod = validityPeriod;
        return this;
    }

    public PromoBuilder setSegment(String segment) {
        this.segment = segment;
        return this;
    }

    public PromoBuilder setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public Promo createPromo() {
        return new Promo(mediaFile, promoCategory, creationalDate, validityPeriod, segment, uuid);
    }
}
