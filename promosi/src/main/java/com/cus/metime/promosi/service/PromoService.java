package com.cus.metime.promosi.service;

import com.cus.metime.promosi.domain.Promo;
import com.cus.metime.promosi.dto.PromoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Service Interface for managing Promo.
 */
public interface PromoService {

    /**
     * Save a promo.
     *
     * @param promoDTO the entity to save
     * @param file
     * @return the persisted entity
     */
    Promo save(PromoDTO promoDTO, MultipartFile file) throws IOException;

    /**
     *  Get all the promos.
     *
     *  @return the list of entities
     */
    List<Promo> findAll();

    List<Promo> findSegmentActivePromos(String segment);

    /**
     *  Get the "id" promo.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Promo findOne(Long id);

    /**
     *  Delete the "id" promo.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    Promo update(PromoDTO promoDTO);
}
