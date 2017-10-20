package com.cus.metime.review.service;

import com.cus.metime.review.domain.HelpFull;
import java.util.List;

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
    HelpFull save(HelpFull helpFull);

    /**
     *  Get all the helpFulls.
     *
     *  @return the list of entities
     */
    List<HelpFull> findAll();

    /**
     *  Get the "id" helpFull.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    HelpFull findOne(Long id);

    /**
     *  Delete the "id" helpFull.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
