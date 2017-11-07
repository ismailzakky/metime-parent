package com.cus.metime.review.dto;

import com.cus.metime.review.domain.CreationalDate;
import com.cus.metime.review.domain.enumeration.Rate;


public class ReviewDTOBuilder {

    private Long id = null;
    private Rate rate = null;
    private String comment = null;
    private String segment1 = null;
    private CreationalDate creationalDate = null;
    private Integer rateOfReview = null;

    public ReviewDTOBuilder() {
    }

    public ReviewDTOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ReviewDTOBuilder setRate(Rate rate) {
        this.rate = rate;
        return this;
    }

    public ReviewDTOBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public ReviewDTOBuilder setSegment1(String segment1) {
        this.segment1 = segment1;
        return this;
    }

    public ReviewDTOBuilder setCreationalDate(CreationalDate creationalDate) {
        this.creationalDate = creationalDate;
        return this;
    }

    public ReviewDTOBuilder setRateOfReview(Integer rateOfReview) {
        this.rateOfReview = rateOfReview;
        return this;
    }

    public ReviewDTO createReviewDTO() {
        return new ReviewDTO(id, rate, comment, segment1, creationalDate, rateOfReview);
    }
    
}
