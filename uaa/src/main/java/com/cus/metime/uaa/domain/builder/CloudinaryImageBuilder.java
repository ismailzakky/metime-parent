package com.cus.metime.uaa.domain.builder;

import com.cus.metime.uaa.domain.CloudinaryImage;

import java.util.Date;

public class CloudinaryImageBuilder {
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

    public CloudinaryImageBuilder setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public CloudinaryImageBuilder setVersion(String version) {
        this.version = version;
        return this;
    }

    public CloudinaryImageBuilder setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public CloudinaryImageBuilder setWidth(Float width) {
        this.width = width;
        return this;
    }

    public CloudinaryImageBuilder setHeight(Float height) {
        this.height = height;
        return this;
    }

    public CloudinaryImageBuilder setFormat(String format) {
        this.format = format;
        return this;
    }

    public CloudinaryImageBuilder setResourceType(String resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public CloudinaryImageBuilder setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public CloudinaryImageBuilder setBytes(Long bytes) {
        this.bytes = bytes;
        return this;
    }

    public CloudinaryImageBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public CloudinaryImageBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public CloudinaryImageBuilder setSecureUrl(String secureUrl) {
        this.secureUrl = secureUrl;
        return this;
    }

    public CloudinaryImageBuilder setEtag(String etag) {
        this.etag = etag;
        return this;
    }

    public CloudinaryImage createCloudinaryImage() {
        return new CloudinaryImage(publicId, version, signature, width, height, format, resourceType, createdAt, bytes, type, url, secureUrl, etag);
    }
}
