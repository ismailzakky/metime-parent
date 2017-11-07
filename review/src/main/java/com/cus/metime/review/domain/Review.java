package com.cus.metime.review.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.cus.metime.review.domain.enumeration.Rate;

/**
 * A Review.
 */
@Entity
@Table(name = "review")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rate")
    private Rate rate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "segment_1")
    private String segment1;

    @OneToMany(mappedBy = "review")
//    @JsonIgnore
    private Set<HelpFull> helpFulnesses = new HashSet<>();
    
    @Embedded
    private CreationalDate creationalDate;

    public Review() {
    }

    public Review(Long id, Rate rate, String comment, String segment1, CreationalDate creationalDate) {
        this.id = id;
        this.rate = rate;
        this.comment = comment;
        this.segment1 = segment1;
        this.creationalDate = creationalDate;
    }

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rate getRate() {
        return rate;
    }

    public Review rate(Rate rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public Review comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSegment1() {
        return segment1;
    }

    public Review segment1(String segment1) {
        this.segment1 = segment1;
        return this;
    }

    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    public Set<HelpFull> getHelpFulnesses() {
        return helpFulnesses;
    }

    public Review helpFulnesses(Set<HelpFull> helpFulls) {
        this.helpFulnesses = helpFulls;
        return this;
    }

    public Review addHelpFulness(HelpFull helpFull) {
        this.helpFulnesses.add(helpFull);
        helpFull.setReview(this);
        return this;
    }

    public Review removeHelpFulness(HelpFull helpFull) {
        this.helpFulnesses.remove(helpFull);
        helpFull.setReview(null);
        return this;
    }

    public void setHelpFulnesses(Set<HelpFull> helpFulls) {
        this.helpFulnesses = helpFulls;
    }

    public CreationalDate getCreationalDate() {
        return creationalDate;
    }

    public void setCreationalDate(CreationalDate creationalDate) {
        this.creationalDate = creationalDate;
    }
    
    
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Review review = (Review) o;
        if (review.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), review.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Review{" +
            "id=" + getId() +
            ", rate='" + getRate() + "'" +
            ", comment='" + getComment() + "'" +
            ", segment1='" + getSegment1() + "'" +
            ", creationalDate='" + getCreationalDate().toString()+ "'" +
            "}";
    }
}
