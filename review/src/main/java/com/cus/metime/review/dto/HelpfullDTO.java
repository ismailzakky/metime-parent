package com.cus.metime.review.dto;

import com.cus.metime.review.domain.CreationalDate;
import com.cus.metime.review.domain.enumeration.HelpFullCategory;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author Handoyo
 */
public class HelpfullDTO {
    private static final long serialVersionUID = 1L;
    private Long id;
    @Enumerated(EnumType.STRING)
    private HelpFullCategory helpfullCategory;
    private CreationalDate creationalDate;
    private ReviewDTO review;

    public HelpfullDTO() {
    }

    public HelpfullDTO(Long id, HelpFullCategory helpfullCategory, CreationalDate creationalDate, ReviewDTO review) {
        this.id = id;
        this.helpfullCategory = helpfullCategory;
        this.creationalDate = creationalDate;
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HelpFullCategory getHelpfullCategory() {
        return helpfullCategory;
    }

    public void setHelpfullCategory(HelpFullCategory helpfullCategory) {
        this.helpfullCategory = helpfullCategory;
    }

    public CreationalDate getCreationalDate() {
        return creationalDate;
    }

    public void setCreationalDate(CreationalDate creationalDate) {
        this.creationalDate = creationalDate;
    }

    public ReviewDTO getReview() {
        return review;
    }

    public void setReview(ReviewDTO review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "HelpfullDTO{" + "id=" + id + ", helpfullCategory=" + helpfullCategory + ", creationalDate=" + creationalDate + ", review=" + review + '}';
    }

    
}
