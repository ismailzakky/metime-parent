package com.cus.metime.review.service.impl;

import com.cus.metime.review.service.HelpFullService;
import com.cus.metime.review.domain.HelpFull;
import com.cus.metime.review.repository.HelpFullRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing HelpFull.
 */
@Service
@Transactional
public class HelpFullServiceImpl implements HelpFullService{

    private final Logger log = LoggerFactory.getLogger(HelpFullServiceImpl.class);

    private final HelpFullRepository helpFullRepository;

    public HelpFullServiceImpl(HelpFullRepository helpFullRepository) {
        this.helpFullRepository = helpFullRepository;
    }

    /**
     * Save a helpFull.
     *
     * @param helpFull the entity to save
     * @return the persisted entity
     */
    @Override
    public HelpFull save(HelpFull helpFull) {
        log.debug("Request to save HelpFull : {}", helpFull);
        return helpFullRepository.save(helpFull);
    }

    /**
     *  Get all the helpFulls.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<HelpFull> findAll() {
        log.debug("Request to get all HelpFulls");
        return helpFullRepository.findAll();
    }

    /**
     *  Get one helpFull by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public HelpFull findOne(Long id) {
        log.debug("Request to get HelpFull : {}", id);
        return helpFullRepository.findOne(id);
    }

    /**
     *  Delete the  helpFull by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete HelpFull : {}", id);
        helpFullRepository.delete(id);
    }
}
