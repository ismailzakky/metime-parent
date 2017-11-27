package com.cus.metime.shared.messaging.promotion;

import java.io.Serializable;
import java.time.LocalDate;

public class PromoMessagingDTO implements Serializable{

    private LocalDate startDate;
    private LocalDate endDate;
    private String salondUUID;
    private String promoUUID;
    private String promoCategory;
    private String imagePublicId;
    private String imageSecurlUrl;
    private String imageUrl;

    public PromoMessagingDTO() {
    }

    public PromoMessagingDTO(LocalDate startDate, LocalDate endDate, String salondUUID, String promoUUID, String promoCategory, String imagePublicId, String imageSecurlUrl, String imageUrl) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.salondUUID = salondUUID;
        this.promoUUID = promoUUID;
        this.promoCategory = promoCategory;
        this.imagePublicId = imagePublicId;
        this.imageSecurlUrl = imageSecurlUrl;
        this.imageUrl = imageUrl;
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

    public String getSalondUUID() {
        return salondUUID;
    }

    public void setSalondUUID(String salondUUID) {
        this.salondUUID = salondUUID;
    }

    public String getPromoUUID() {
        return promoUUID;
    }

    public void setPromoUUID(String promoUUID) {
        this.promoUUID = promoUUID;
    }

    public String getPromoCategory() {
        return promoCategory;
    }

    public void setPromoCategory(String promoCategory) {
        this.promoCategory = promoCategory;
    }

    public String getImagePublicId() {
        return imagePublicId;
    }

    public void setImagePublicId(String imagePublicId) {
        this.imagePublicId = imagePublicId;
    }

    public String getImageSecurlUrl() {
        return imageSecurlUrl;
    }

    public void setImageSecurlUrl(String imageSecurlUrl) {
        this.imageSecurlUrl = imageSecurlUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
