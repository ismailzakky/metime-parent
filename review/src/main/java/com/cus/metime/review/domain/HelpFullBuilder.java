/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cus.metime.review.domain;

import com.cus.metime.review.domain.enumeration.HelpFullCategory;


public class HelpFullBuilder {

    private Long id = null;
    private HelpFullCategory helpfullCategory = null;
    private Review review = null;
    private CreationalDate creationalDate = null;

    public HelpFullBuilder() {
    }

    public HelpFullBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public HelpFullBuilder setHelpfullCategory(HelpFullCategory helpfullCategory) {
        this.helpfullCategory = helpfullCategory;
        return this;
    }

    public HelpFullBuilder setReview(Review review) {
        this.review = review;
        return this;
    }

    public HelpFullBuilder setCreationalDate(CreationalDate creationalDate) {
        this.creationalDate = creationalDate;
        return this;
    }

    public HelpFull createHelpFull() {
        return new HelpFull(id, helpfullCategory, review, creationalDate);
    }
    
}
