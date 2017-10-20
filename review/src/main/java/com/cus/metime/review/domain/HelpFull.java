package com.cus.metime.review.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import com.cus.metime.review.domain.enumeration.HelpFullCategory;

/**
 * A HelpFull.
 */
@Entity
@Table(name = "help_full")
public class HelpFull implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "creational_date")
    private String creationalDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "helpfull_category")
    private HelpFullCategory helpfullCategory;

    @ManyToOne
    private Review review;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreationalDate() {
        return creationalDate;
    }

    public HelpFull creationalDate(String creationalDate) {
        this.creationalDate = creationalDate;
        return this;
    }

    public void setCreationalDate(String creationalDate) {
        this.creationalDate = creationalDate;
    }

    public HelpFullCategory getHelpfullCategory() {
        return helpfullCategory;
    }

    public HelpFull helpfullCategory(HelpFullCategory helpfullCategory) {
        this.helpfullCategory = helpfullCategory;
        return this;
    }

    public void setHelpfullCategory(HelpFullCategory helpfullCategory) {
        this.helpfullCategory = helpfullCategory;
    }

    public Review getReview() {
        return review;
    }

    public HelpFull review(Review review) {
        this.review = review;
        return this;
    }

    public void setReview(Review review) {
        this.review = review;
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
        HelpFull helpFull = (HelpFull) o;
        if (helpFull.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), helpFull.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HelpFull{" +
            "id=" + getId() +
            ", creationalDate='" + getCreationalDate() + "'" +
            ", helpfullCategory='" + getHelpfullCategory() + "'" +
            "}";
    }
}
