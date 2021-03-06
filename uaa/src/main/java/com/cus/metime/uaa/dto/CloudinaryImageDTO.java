package com.cus.metime.uaa.dto;

import com.cus.metime.uaa.domain.CloudinaryImage;

import java.util.Date;

public class CloudinaryImageDTO extends CloudinaryImage {

    public CloudinaryImageDTO() {
    }

    public CloudinaryImageDTO(String publicId, String version, String signature, Float width, Float height, String format, String resourceType, Date createdAt, Long bytes, String type, String url, String secureUrl, String etag) {
        super(publicId, version, signature, width, height, format, resourceType, createdAt, bytes, type, url, secureUrl, etag);
    }
}
