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

    @Column(name = "jhi_comment")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "rate")
    private Rate rate;

    @OneToMany(mappedBy = "review")
    @JsonIgnore
    private Set<HelpFull> helpFullnesses = new HashSet<>();

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<HelpFull> getHelpFullnesses() {
        return helpFullnesses;
    }

    public Review helpFullnesses(Set<HelpFull> helpFulls) {
        this.helpFullnesses = helpFulls;
        return this;
    }

    public Review addHelpFullness(HelpFull helpFull) {
        this.helpFullnesses.add(helpFull);
        helpFull.setReview(this);
        return this;
    }

    public Review removeHelpFullness(HelpFull helpFull) {
        this.helpFullnesses.remove(helpFull);
        helpFull.setReview(null);
        return this;
    }

    public void setHelpFullnesses(Set<HelpFull> helpFulls) {
        this.helpFullnesses = helpFulls;
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
            ", comment='" + getComment() + "'" +
            ", rate='" + getRate() + "'" +
            "}";
    }
}
