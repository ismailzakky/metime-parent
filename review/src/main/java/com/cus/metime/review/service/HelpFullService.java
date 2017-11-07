package com.cus.metime.review.service;

import com.cus.metime.review.domain.HelpFull;
import com.cus.metime.review.dto.HelpfullDTO;
import java.util.List;
import java.util.Set;

/**
 * Service Interface for managing HelpFull.
 */
public interface HelpFullService {

    /**
     * Save a helpFull.
     *
     * @param helpFull the entity to save
     * @return the persisted entity
     */
    HelpfullDTO save(HelpfullDTO helpFull);

    /**
     *  Get all the helpFulls.
     *
     *  @return the list of entities
     */
    List<HelpfullDTO> findAll();

    /**
     *  Get the "id" helpFull.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    HelpfullDTO findOne(Long id);

    /**
     *  Delete the "id" helpFull.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
