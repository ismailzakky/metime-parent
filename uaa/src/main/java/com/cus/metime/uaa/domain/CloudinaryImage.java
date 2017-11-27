package com.cus.metime.uaa.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class CloudinaryImage implements Serializable{

    @Column(unique = true)
    private String publicId;

    @GenericGenerator(name = "generator" , strategy = "foreign",parameters = @org.hibernate.annotations.Parameter(name = "id" ,value = "image"))
    private Long foreignID;


    @Size(max = 50)
    private String version;
    @Size(max = 50)
    private String signature;
    @DecimalMin("0.00")
    private Float width;
    @DecimalMin("0.00")
    private Float height;
    @Size(max = 50)
    private String format;
    @Size(max = 50)
    private String resourceType;
    private Date createdAt;
    private Long bytes;
    @Size(max = 50)
    private String type;
    @Size(max = 50)
    private String url;
    @Size(max = 50)
    private String secureUrl;
    @Size(max = 50)
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

    public Long getForeignID() {
        return foreignID;
    }

    public void setForeignID(Long foreignID) {
        this.foreignID = foreignID;
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
