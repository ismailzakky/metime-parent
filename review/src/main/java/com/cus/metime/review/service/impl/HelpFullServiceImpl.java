package com.cus.metime.review.service.impl;

import com.cus.metime.review.domain.CreationalDate;
import com.cus.metime.review.domain.HelpFull;
import com.cus.metime.review.service.HelpFullService;
import com.cus.metime.review.dto.HelpfullDTO;
import com.cus.metime.review.dto.assembler.HelpfullAssembler;
import com.cus.metime.review.repository.HelpFullRepository;
import java.util.Date;
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
    public HelpfullDTO save(HelpfullDTO helpFull) {
        CreationalDate creationalDate = new CreationalDate();
        if (helpFull.getId() == null) {
            creationalDate.setCreatedAt(new Date());
            creationalDate.setCreatedBy(helpFull.getCreationalDate().getCreatedBy());
            creationalDate.setModifiedAt(new Date());
            creationalDate.setModifiedBy(helpFull.getCreationalDate().getCreatedBy());
        } else {
            HelpfullDTO reviewExisting = findOne(helpFull.getId());
            creationalDate.setCreatedAt(reviewExisting.getCreationalDate().getCreatedAt());
            creationalDate.setCreatedBy(reviewExisting.getCreationalDate().getCreatedBy());
            creationalDate.setModifiedAt(new Date());
            creationalDate.setModifiedBy(helpFull.getCreationalDate().getModifiedBy());
        }
        helpFull.setCreationalDate(creationalDate);
        log.debug("Request to save HelpFull : {}", helpFull);
        return new HelpfullAssembler()
                .toDTO(helpFullRepository
                        .save(new HelpfullAssembler().toDomain(helpFull)));
    }

    /**
     *  Get all the helpFulls.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<HelpfullDTO> findAll() {
        log.debug("Request to get all HelpFulls");
        return new HelpfullAssembler()
                .toDTOs(helpFullRepository.findAll());
    }

    /**
     *  Get one helpFull by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public HelpfullDTO findOne(Long id) {
        log.debug("Request to get HelpFull : {}", id);
        HelpFull helpFull = helpFullRepository.findOne(id);
        if (helpFull == null) {
            helpFull = new HelpFull();
        }
        return new HelpfullAssembler()
                .toDTO(helpFull);
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
