package com.cus.metime.promosi.Assembler;

import com.cus.metime.promosi.Assembler.core.ObjectAssembler;
import com.cus.metime.promosi.domain.CloudinaryImage;
import com.cus.metime.promosi.domain.embeddable.builder.CloudinaryImageBuilder;
import com.cus.metime.promosi.dto.CloudinaryImageDTO;
import com.cus.metime.promosi.dto.builder.CloudinaryImageDTOBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CloudinaryImageAssembler implements ObjectAssembler<CloudinaryImage,CloudinaryImageDTO> {
    @Override
    public CloudinaryImage toDomain(CloudinaryImageDTO dtoObject) {
        return new CloudinaryImageBuilder()
                .setPublicId(dtoObject.getPublicId())
                .setVersion(dtoObject.getVersion())
                .setSignature(dtoObject.getSignature())
                .setWidth(dtoObject.getWidth())
                .setHeight(dtoObject.getHeight())
                .setFormat(dtoObject.getFormat())
                .setResourceType(dtoObject.getResourceType())
                .setCreatedAt(dtoObject.getCreatedAt())
                .setBytes(dtoObject.getBytes())
                .setType(dtoObject.getType())
                .setUrl(dtoObject.getUrl())
                .setSecureUrl(dtoObject.getSecureUrl())
                .setEtag(dtoObject.getEtag())
                .createCloudinaryImage();
    }

    @Override
    public CloudinaryImageDTO toDTO(CloudinaryImage domainObject) {
        return new CloudinaryImageDTOBuilder()
                .setPublicId(domainObject.getPublicId())
                .setVersion(domainObject.getVersion())
                .setSignature(domainObject.getSignature())
                .setWidth(domainObject.getWidth())
                .setHeight(domainObject.getHeight())
                .setFormat(domainObject.getFormat())
                .setResourceType(domainObject.getResourceType())
                .setCreatedAt(domainObject.getCreatedAt())
                .setBytes(domainObject.getBytes())
                .setType(domainObject.getType())
                .setUrl(domainObject.getUrl())
                .setSecureUrl(domainObject.getSecureUrl())
                .setEtag(domainObject.getEtag())
                .createCloudinaryImageDTO();
    }

    @Override
    public List<CloudinaryImage> toDomainList(List<CloudinaryImageDTO> dtoObjectList) {
        return null;
    }

    @Override
    public List<CloudinaryImageDTO> toDTOList(List<CloudinaryImage> domainObjectList) {
        return null;
    }
}
