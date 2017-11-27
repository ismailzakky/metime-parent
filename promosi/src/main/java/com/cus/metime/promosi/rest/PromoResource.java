package com.cus.metime.promosi.rest;

import com.codahale.metrics.annotation.Timed;
import com.cus.metime.promosi.domain.Promo;
import com.cus.metime.promosi.dto.PromoDTO;
import com.cus.metime.promosi.service.AssyncMessagingService;
import com.cus.metime.promosi.service.PromoService;
import com.cus.metime.promosi.rest.util.HeaderUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Promo.
 */
@RestController
@RequestMapping("/api")
public class PromoResource {

    private final Logger log = LoggerFactory.getLogger(PromoResource.class);

    private static final String ENTITY_NAME = "promo";

    private final PromoService promoService;

    private final AssyncMessagingService assyncMessagingService;

    public PromoResource(PromoService promoService, AssyncMessagingService assyncMessagingService) {
        this.promoService = promoService;
        this.assyncMessagingService = assyncMessagingService;
    }

    /**
     * POST  /promos : Create a new promo.
     *
     * @param param the promo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new promo, or with status 400 (Bad Request) if the promo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(value = "/promo")
    @Timed
    public ResponseEntity<Promo> createPromo(@RequestParam("param") String param, @RequestParam("file") MultipartFile file) throws URISyntaxException, IOException {



        PromoDTO promoDTO = new ObjectMapper().readValue(param, PromoDTO.class);

        if( StringUtils.isEmpty(promoDTO.getPromoCategory().toString()) || promoDTO.getValidityPeriodDTO().getEndDate() == null || promoDTO.getValidityPeriodDTO().getStartDate() == null || StringUtils.isEmpty(promoDTO.getSegment())  ){
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "invalid parameter", "Invalid param Parameter Format")).body(null);
        }
        log.debug("REST request to save Promo : {}", promoDTO);
        if (promoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new promo cannot already have an ID")).body(null);
        }

        Promo result = promoService.save(promoDTO,file);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /promos : Updates an existing promo.
     *
     * @param promoDTO the promo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated promo,
     * or with status 400 (Bad Request) if the promo is not valid,
     * or with status 500 (Internal Server Error) if the promo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/promo")
    @Timed
    public ResponseEntity<Promo> updatePromo(@RequestBody PromoDTO promoDTO) throws URISyntaxException {
        log.debug("REST request to update Promo : {}", promoDTO);

        if( StringUtils.isEmpty(promoDTO.getPromoCategory().toString()) || promoDTO.getValidityPeriodDTO().getEndDate() == null || promoDTO.getValidityPeriodDTO().getStartDate() == null || StringUtils.isEmpty(promoDTO.getSegment())  || StringUtils.isEmpty(promoDTO.getId()) || StringUtils.isEmpty(promoDTO.getUuid()) ){
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "invalid parameter", "Invalid param Parameter Format")).body(null);
        }

        Promo promo = promoService.findOne(promoDTO.getId());
        if (promoDTO.getId() == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "notexist", "selected promo is'nt exist")).body(null);
        }
        else if(promo == null){
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "notexist", "selected promo is'nt exist")).body(null);
        }

        Promo result = promoService.update(promoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, promoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /promos : get all the promos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of promos in body
     */
    @GetMapping("/promoList")
    @Timed
    public List<Promo> getAllPromos() {
        log.debug("REST request to get all Promos");
        return promoService.findAll();
        }


    @GetMapping("/promoList/{segmentId}")
    @Timed
    public List<Promo> getSegmentActivePromo(@PathVariable("segmentId") String segment){
        log.debug("REST request to get all active Promos");
        return promoService.findSegmentActivePromos(segment);

    }

    /**
     * GET  /promos/:id : get the "id" promo.
     *
     * @param id the id of the promo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the promo, or with status 404 (Not Found)
     */
    @GetMapping("/promo/{id}")
    @Timed
    public ResponseEntity<Promo> getPromo(@PathVariable Long id) {
        log.debug("REST request to get Promo : {}", id);
        Promo promo = promoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(promo));
    }

    /**
     * DELETE  /promos/:id : delete the "id" promo.
     *
     * @param id the id of the promo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/promo/{id}")
    @Timed
    public ResponseEntity<Void> deletePromo(@PathVariable Long id) {
        try {
            if( id == null || StringUtils.isEmpty(id.toString() )){
                return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "invalid parameter", "Invalid param Parameter Format")).body(null);
            }
        } catch (NullPointerException e){
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "invalid parameter", "Invalid param Parameter Format")).body(null);

        }

        log.debug("REST request to delete Promo : {}", id);
        promoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
