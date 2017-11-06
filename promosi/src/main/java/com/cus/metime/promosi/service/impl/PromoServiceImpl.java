package com.cus.metime.promosi.service.impl;

import com.cus.metime.promosi.Assembler.PromoAssembler;
import com.cus.metime.promosi.domain.Promo;
import com.cus.metime.promosi.dto.CreationalDateDTO;
import com.cus.metime.promosi.dto.MessageEvent;
import com.cus.metime.promosi.dto.PromoDTO;
import com.cus.metime.promosi.dto.builder.CreationalDateDTOBuilder;
import com.cus.metime.promosi.repository.PromoRepository;
import com.cus.metime.promosi.security.SecurityUtils;
import com.cus.metime.promosi.service.AssyncMessagingService;
import com.cus.metime.promosi.service.PromoService;
import com.cus.metime.shared.util.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
        boolean successSaving = false;
        Promo savedPromo = null;
        RandomString randomString = new RandomString(10, ThreadLocalRandom.current());
        String fileName = randomString.nextString();
        promoDTO.setMediaFile(fileName);
        promoDTO.setUuid(UUID.randomUUID().toString());
        try{
            savedPromo = promoRepository.save(promoAssembler.toDomain(promoDTO));
            successSaving = true;
        } catch (Exception e){
            log.debug("failed to save data");
            e.printStackTrace();
        } finally {
            if(successSaving){
                assyncMessagingService.sendImageFile(file, fileName,savedPromo, MessageEvent.CREATE);
                assyncMessagingService.sendIndexMessage(MessageEvent.CREATE,savedPromo);
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
                    assyncMessagingService.sendImageFile(null,null, currentPromo, MessageEvent.DELETE);
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
