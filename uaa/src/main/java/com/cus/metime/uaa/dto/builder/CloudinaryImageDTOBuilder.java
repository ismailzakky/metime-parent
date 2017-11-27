package com.cus.metime.uaa.dto.builder;

import com.cus.metime.uaa.dto.CloudinaryImageDTO;

import java.util.Date;

public class CloudinaryImageDTOBuilder {
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

    public CloudinaryImageDTOBuilder setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public CloudinaryImageDTOBuilder setVersion(String version) {
        this.version = version;
        return this;
    }

    public CloudinaryImageDTOBuilder setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public CloudinaryImageDTOBuilder setWidth(Float width) {
        this.width = width;
        return this;
    }

    public CloudinaryImageDTOBuilder setHeight(Float height) {
        this.height = height;
        return this;
    }

    public CloudinaryImageDTOBuilder setFormat(String format) {
        this.format = format;
        return this;
    }

    public CloudinaryImageDTOBuilder setResourceType(String resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public CloudinaryImageDTOBuilder setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public CloudinaryImageDTOBuilder setBytes(Long bytes) {
        this.bytes = bytes;
        return this;
    }

    public CloudinaryImageDTOBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public CloudinaryImageDTOBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public CloudinaryImageDTOBuilder setSecureUrl(String secureUrl) {
        this.secureUrl = secureUrl;
        return this;
    }

    public CloudinaryImageDTOBuilder setEtag(String etag) {
        this.etag = etag;
        return this;
    }

    public CloudinaryImageDTO createCloudinaryImageDTO() {
        return new CloudinaryImageDTO(publicId, version, signature, width, height, format, resourceType, createdAt, bytes, type, url, secureUrl, etag);
    }
}
