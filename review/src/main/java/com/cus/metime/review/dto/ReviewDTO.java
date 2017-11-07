package com.cus.metime.review.dto;

import com.cus.metime.review.domain.CreationalDate;
import com.cus.metime.review.domain.enumeration.Rate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author Handoyo
 */
public class ReviewDTO {
    private static final long serialVersionUID = 1L;
    private Long id;

    @Enumerated(EnumType.STRING)
    private Rate rate;

    private String comment;

    private String segment1;

    private Set<HelpfullDTO> helpFulnesses = new HashSet<>();
    
    private CreationalDate creationalDate;
    
    private Integer rateOfReview;

    public ReviewDTO() {
    }

    public ReviewDTO(Long id, Rate rate, String comment, String segment1, CreationalDate creationalDate, Integer rateOfReview) {
        this.id = id;
        this.rate = rate;
        this.comment = comment;
        this.segment1 = segment1;
        this.creationalDate = creationalDate;
        this.rateOfReview = rateOfReview;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSegment1() {
        return segment1;
    }

    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    public Set<HelpfullDTO> getHelpFulnesses() {
        return helpFulnesses;
    }

    public void setHelpFulnesses(Set<HelpfullDTO> helpFulnesses) {
        this.helpFulnesses = helpFulnesses;
    }

    public CreationalDate getCreationalDate() {
        return creationalDate;
    }

    public void setCreationalDate(CreationalDate creationalDate) {
        this.creationalDate = creationalDate;
    }

    public Integer getRateOfReview() {
        return rateOfReview;
    }

    public void setRateOfReview(Integer rateOfReview) {
        this.rateOfReview = rateOfReview;
    }

}
