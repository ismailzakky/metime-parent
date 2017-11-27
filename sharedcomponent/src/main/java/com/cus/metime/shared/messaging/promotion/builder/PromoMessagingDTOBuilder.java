package com.cus.metime.shared.messaging.promotion.builder;

import com.cus.metime.shared.messaging.promotion.PromoMessagingDTO;

import java.time.LocalDate;

public class PromoMessagingDTOBuilder {
    private LocalDate startDate;
    private LocalDate endDate;
    private String salondUUID;
    private String promoUUID;
    private String promoCategory;
    private String imagePublicId;
    private String imageSecurlUrl;
    private String imageUrl;

    public PromoMessagingDTOBuilder setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public PromoMessagingDTOBuilder setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public PromoMessagingDTOBuilder setSalondUUID(String salondUUID) {
        this.salondUUID = salondUUID;
        return this;
    }

    public PromoMessagingDTOBuilder setPromoUUID(String promoUUID) {
        this.promoUUID = promoUUID;
        return this;
    }

    public PromoMessagingDTOBuilder setPromoCategory(String promoCategory) {
        this.promoCategory = promoCategory;
        return this;
    }

    public PromoMessagingDTOBuilder setImagePublicId(String imagePublicId) {
        this.imagePublicId = imagePublicId;
        return this;
    }

    public PromoMessagingDTOBuilder setImageSecurlUrl(String imageSecurlUrl) {
        this.imageSecurlUrl = imageSecurlUrl;
        return this;
    }

    public PromoMessagingDTOBuilder setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public PromoMessagingDTO createPromoMessagingDTO() {
        return new PromoMessagingDTO(startDate, endDate, salondUUID, promoUUID, promoCategory, imagePublicId, imageSecurlUrl, imageUrl);
    }
}