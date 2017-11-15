package com.cus.metime.promosi.domain;


import com.cus.metime.promosi.domain.embeddable.CreationalDate;
import com.cus.metime.promosi.domain.embeddable.ValidityPeriod;
import com.cus.metime.promosi.domain.enumeration.PromoCategory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Promo.
 */
@Entity
@Table(name = "promo")
public class Promo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "media_file")
    private String mediaFile;

    @Enumerated(EnumType.STRING)
    @Column(name = "promo_category")
    private PromoCategory promoCategory;

    @Embedded
    private CreationalDate creationalDate;

    @Embedded
    private ValidityPeriod validityPeriod;

    @Column(name = "segment")
    private String segment;

    @Column(name = "uuid")
    private String uuid;

    @OneToOne
    private CloudinaryImage cloudinaryImage;


    public Promo() {
    }

    public Promo(String mediaFile, PromoCategory promoCategory, CreationalDate creationalDate, ValidityPeriod validityPeriod, String segment, String uuid, CloudinaryImage cloudinaryImage) {
        this.mediaFile = mediaFile;
        this.promoCategory = promoCategory;
        this.creationalDate = creationalDate;
        this.validityPeriod = validityPeriod;
        this.segment = segment;
        this.uuid = uuid;
        this.cloudinaryImage = cloudinaryImage;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMediaFile() {
        return mediaFile;
    }

    public Promo mediaFile(String mediaFile) {
        this.mediaFile = mediaFile;
        return this;
    }

    public void setMediaFile(String mediaFile) {
        this.mediaFile = mediaFile;
    }

    public PromoCategory getPromoCategory() {
        return promoCategory;
    }

    public Promo promoCategory(PromoCategory promoCategory) {
        this.promoCategory = promoCategory;
        return this;
    }

    public ValidityPeriod getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(ValidityPeriod validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public void setPromoCategory(PromoCategory promoCategory) {
        this.promoCategory = promoCategory;
    }
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove


    public CreationalDate getCreationalDate() {
        return creationalDate;
    }

    public void setCreationalDate(CreationalDate creationalDate) {
        this.creationalDate = creationalDate;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public CloudinaryImage getCloudinaryImage() {
        return cloudinaryImage;
    }

    public void setCloudinaryImage(CloudinaryImage cloudinaryImage) {
        this.cloudinaryImage = cloudinaryImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Promo promo = (Promo) o;
        if (promo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), promo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Promo{" +
                "id=" + id +
                ", mediaFile='" + mediaFile + '\'' +
                ", promoCategory=" + promoCategory +
                ", creationalDate=" + creationalDate +
                ", validityPeriod=" + validityPeriod +
                ", segment='" + segment + '\'' +
                ", uuid='" + uuid + '\'' +
                ", cloudinaryImage=" + cloudinaryImage +
                '}';
    }
}
