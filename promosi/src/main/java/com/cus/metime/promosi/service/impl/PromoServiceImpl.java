package com.cus.metime.promosi.service.impl;

import com.cus.metime.promosi.Assembler.PromoAssembler;
import com.cus.metime.promosi.domain.Promo;
import com.cus.metime.promosi.domain.enumeration.PromoCategory;
import com.cus.metime.promosi.dto.CloudinaryImageDTO;
import com.cus.metime.promosi.dto.CreationalDateDTO;
import com.cus.metime.promosi.dto.PromoDTO;
import com.cus.metime.promosi.dto.builder.CloudinaryImageDTOBuilder;
import com.cus.metime.promosi.dto.builder.CreationalDateDTOBuilder;
import com.cus.metime.promosi.repository.PromoRepository;
import com.cus.metime.promosi.security.SecurityUtils;
import com.cus.metime.promosi.service.AssyncMessagingService;
import com.cus.metime.promosi.service.PromoService;
import com.cus.metime.shared.messaging.MessageEvent;
import com.cus.metime.shared.services.CloudinaryFileHandling;
import com.cloudinary.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.plugin2.message.EventMessage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Service Implementation for managing Promo.
 */
@Service
@Transactional
public class PromoServiceImpl implements PromoService{

    private final Logger log = LoggerFactory.getLogger(PromoServiceImpl.class);

    @Autowired
    private PromoRepository promoRepository;
    @Autowired
    private PromoAssembler promoAssembler;
    @Autowired
    private AssyncMessagingService assyncMessagingService;
    @Value("${cloudinary}")
    private String cloudinaryConnectionString;


    /**
     * Save a promo.
     *
     * @param promoDTO the entity to save
     * @param file
     * @return the persisted entity
     */
    @Override
    public Promo save(PromoDTO promoDTO, MultipartFile file) throws IOException {
        log.debug("Request to save Promo : {}", promoDTO);
        String currentUser = SecurityUtils.getCurrentUserLogin();
        CreationalDateDTO creationalDateDTO = new CreationalDateDTOBuilder().setCreatedAt(LocalDateTime.now()).setCreatedBy(currentUser).setModifiedBy(currentUser).setModifiedAt(LocalDateTime.now()).createCreationalDateDTO();
        promoDTO.setCreationalDateDTO(creationalDateDTO);
        boolean successUpload = false;
        Promo savedPromo = null;

        CloudinaryFileHandling cloudinaryFileHandling = new CloudinaryFileHandling(cloudinaryConnectionString);
        if(promoDTO.getPromoCategory() == PromoCategory.CUSTOM){
            try{
                Map resultMap = cloudinaryFileHandling.uploadIMage(file);
                CloudinaryImageDTO cloudinaryImageDTO = new CloudinaryImageDTOBuilder()
                        .setPublicId((String) resultMap.get("public_Id"))
                        .setVersion((String) resultMap.get("version"))
                        .setSignature((String) resultMap.get("signature"))
                        .setWidth(Float.parseFloat( (String) resultMap.get("width")))
                        .setHeight(Float.parseFloat( (String) resultMap.get("height")))
                        .setFormat((String) resultMap.get("format"))
                        .setResourceType((String) resultMap.get("resource_type"))
                        .setBytes(Long.parseLong((String) resultMap.get("bytes")))
                        .setType((String) resultMap.get("type"))
                        .setUrl((String) resultMap.get("url"))
                        .setSecureUrl((String) resultMap.get("secure_url"))
                        .setEtag((String)resultMap.get("etag"))
                        .createCloudinaryImageDTO();
                promoDTO.setCloudinaryImageDTO(cloudinaryImageDTO);
                successUpload = true;
            } catch (Exception e){
                e.printStackTrace();
                successUpload = false;
            } finally {
                if(successUpload){
                    savedPromo = promoRepository.save(promoAssembler.toDomain(promoDTO));
                }
            }
        } else {
            try {
                savedPromo = promoRepository.save(promoAssembler.toDomain(promoDTO));
                assyncMessagingService.sendIndexMessage(MessageEvent.CREATE,savedPromo);
            } catch (Exception e){
                e.printStackTrace();
                savedPromo = null;
            }
        }
        return savedPromo;
    }

    /**
     *  Get all the promos.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Promo> findAll() {
        log.debug("Request to get all active Promos");
        return promoRepository.findAll();

    }

    @Override
    @Transactional(readOnly = true)
    public List<Promo> findSegmentActivePromos(String segment) {
        LocalDate date = LocalDate.now();
        return promoRepository.findByStartDateAfterAndEndDateBefore(date,segment);
    }

    /**
     *  Get one promo by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Promo findOne(Long id) {
        log.debug("Request to get Promo : {}", id);
        return promoRepository.findOne(id);
    }

    /**
     *  Delete the  promo by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Promo : {}", id);
        Promo currentPromo = null;
        boolean success = false;
        CloudinaryFileHandling cloudinaryFileHandling = new CloudinaryFileHandling(cloudinaryConnectionString);
        try{
            currentPromo = promoRepository.findOne(id);
            promoRepository.delete(id);
            success = true;
        } catch (Exception e){
            log.debug("failed to delete data");
            e.printStackTrace();
        } finally {
            if(success){
                try {
                    assyncMessagingService.sendIndexMessage(MessageEvent.DELETE,currentPromo);
                    cloudinaryFileHandling.deleteImage(currentPromo.getCloudinaryImage().getPublicId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Promo update(PromoDTO promoDTO) {
        Promo current = promoRepository.findOne(promoDTO.getId());
        PromoDTO currentDTO = promoAssembler.toDTO(current);
        promoDTO.setCreationalDateDTO(currentDTO.getCreationalDateDTO());
        promoDTO.setValidityPeriodDTO(currentDTO.getValidityPeriodDTO());
        Promo promo = promoAssembler.toDomain(promoDTO);
        promo.setMediaFile(current.getMediaFile());


        String currentUser = SecurityUtils.getCurrentUserLogin();
        promo.setUuid(current.getUuid());
        promo.getCreationalDate().setModifiedBy(currentUser);
        promo.getCreationalDate().setModifiedAt(LocalDateTime.now());

        boolean success = false;

        try{
            promoRepository.save(promo);
            success = true;
        } catch(Exception e){
            log.debug("failed to update promo",promo);
            e.printStackTrace();
        } finally {
            if(success){
                assyncMessagingService.sendIndexMessage(MessageEvent.UPDATE,promo);
            }
        }

        return promo;
    }
}
