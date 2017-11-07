/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cus.metime.review.domain;

import com.cus.metime.review.domain.enumeration.Rate;


public class ReviewBuilder {

    private Long id = null;
    private Rate rate = null;
    private String comment = null;
    private String segment1;
    private CreationalDate creationalDate = null;

    public ReviewBuilder() {
    }

    public ReviewBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ReviewBuilder setRate(Rate rate) {
        this.rate = rate;
        return this;
    }

    public ReviewBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public ReviewBuilder setSegment1(String segment1) {
        this.segment1 = segment1;
        return this;
    }

    public ReviewBuilder setCreationalDate(CreationalDate creationalDate) {
        this.creationalDate = creationalDate;
        return this;
    }

    public Review createReview() {
        return new Review(id, rate, comment, segment1, creationalDate);
    }
    
}
