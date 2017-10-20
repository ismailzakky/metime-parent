package com.cus.metime.review.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cus.metime.review.domain.HelpFull;
import com.cus.metime.review.service.HelpFullService;
import com.cus.metime.review.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing HelpFull.
 */
@RestController
@RequestMapping("/api")
public class HelpFullResource {

    private final Logger log = LoggerFactory.getLogger(HelpFullResource.class);

    private static final String ENTITY_NAME = "helpFull";

    private final HelpFullService helpFullService;

    public HelpFullResource(HelpFullService helpFullService) {
        this.helpFullService = helpFullService;
    }

    /**
     * POST  /help-fulls : Create a new helpFull.
     *
     * @param helpFull the helpFull to create
     * @return the ResponseEntity with status 201 (Created) and with body the new helpFull, or with status 400 (Bad Request) if the helpFull has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/help-fulls")
    @Timed
    public ResponseEntity<HelpFull> createHelpFull(@RequestBody HelpFull helpFull) throws URISyntaxException {
        log.debug("REST request to save HelpFull : {}", helpFull);
        if (helpFull.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new helpFull cannot already have an ID")).body(null);
        }
        HelpFull result = helpFullService.save(helpFull);
        return ResponseEntity.created(new URI("/api/help-fulls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /help-fulls : Updates an existing helpFull.
     *
     * @param helpFull the helpFull to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated helpFull,
     * or with status 400 (Bad Request) if the helpFull is not valid,
     * or with status 500 (Internal Server Error) if the helpFull couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/help-fulls")
    @Timed
    public ResponseEntity<HelpFull> updateHelpFull(@RequestBody HelpFull helpFull) throws URISyntaxException {
        log.debug("REST request to update HelpFull : {}", helpFull);
        if (helpFull.getId() == null) {
            return createHelpFull(helpFull);
        }
        HelpFull result = helpFullService.save(helpFull);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, helpFull.getId().toString()))
            .body(result);
    }

    /**
     * GET  /help-fulls : get all the helpFulls.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of helpFulls in body
     */
    @GetMapping("/help-fulls")
    @Timed
    public List<HelpFull> getAllHelpFulls() {
        log.debug("REST request to get all HelpFulls");
        return helpFullService.findAll();
        }

    /**
     * GET  /help-fulls/:id : get the "id" helpFull.
     *
     * @param id the id of the helpFull to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the helpFull, or with status 404 (Not Found)
     */
    @GetMapping("/help-fulls/{id}")
    @Timed
    public ResponseEntity<HelpFull> getHelpFull(@PathVariable Long id) {
        log.debug("REST request to get HelpFull : {}", id);
        HelpFull helpFull = helpFullService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(helpFull));
    }

    /**
     * DELETE  /help-fulls/:id : delete the "id" helpFull.
     *
     * @param id the id of the helpFull to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/help-fulls/{id}")
    @Timed
    public ResponseEntity<Void> deleteHelpFull(@PathVariable Long id) {
        log.debug("REST request to delete HelpFull : {}", id);
        helpFullService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
