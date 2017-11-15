package com.cus.metime.promosi.domain;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class CloudinaryImage implements Serializable{

    @Id
    private String publicId;
    private String version;
    private String signature;
    private Float width;
    private Float height;
    private String format;
    private String resourceType;
    private Date createdAt;
    private Long bytes;
    private String type;
    private String url;
    private String secureUrl;
    private String etag;

    public CloudinaryImage() {
    }


    public CloudinaryImage(String publicId, String version, String signature, Float width, Float height, String format, String resourceType, Date createdAt, Long bytes, String type, String url, String secureUrl, String etag) {
        this.publicId = publicId;
        this.version = version;
        this.signature = signature;
        this.width = width;
        this.height = height;
        this.format = format;
        this.resourceType = resourceType;
        this.createdAt = createdAt;
        this.bytes = bytes;
        this.type = type;
        this.url = url;
        this.secureUrl = secureUrl;
        this.etag = etag;
    }

    public String getPublicId() {

        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getBytes() {
        return bytes;
    }

    public void setBytes(Long bytes) {
        this.bytes = bytes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSecureUrl() {
        return secureUrl;
    }

    public void setSecureUrl(String secureUrl) {
        this.secureUrl = secureUrl;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    @Override
    public String toString() {
        return "CloudinaryImage{" +
                "publicId='" + publicId + '\'' +
                ", version='" + version + '\'' +
                ", signature='" + signature + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", format='" + format + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", createdAt=" + createdAt +
                ", bytes=" + bytes +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", secureUrl='" + secureUrl + '\'' +
                ", etag='" + etag + '\'' +
                '}';
    }
}
