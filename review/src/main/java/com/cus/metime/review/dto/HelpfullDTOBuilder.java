/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cus.metime.review.dto;

import com.cus.metime.review.domain.CreationalDate;
import com.cus.metime.review.domain.enumeration.HelpFullCategory;


public class HelpfullDTOBuilder {

    private Long id = null;
    private HelpFullCategory helpfullCategory = null;
    private CreationalDate creationalDate = null;
    private ReviewDTO review = null;

    public HelpfullDTOBuilder() {
    }

    public HelpfullDTOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public HelpfullDTOBuilder setHelpfullCategory(HelpFullCategory helpfullCategory) {
        this.helpfullCategory = helpfullCategory;
        return this;
    }

    public HelpfullDTOBuilder setCreationalDate(CreationalDate creationalDate) {
        this.creationalDate = creationalDate;
        return this;
    }

    public HelpfullDTOBuilder setReview(ReviewDTO review) {
        this.review = review;
        return this;
    }

    public HelpfullDTO createHelpfullDTO() {
        return new HelpfullDTO(id, helpfullCategory, creationalDate, review);
    }
    
}
