package com.cus.metime.review.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cus.metime.review.domain.Review;
import com.cus.metime.review.service.ReviewService;
import com.cus.metime.review.web.rest.util.HeaderUtil;
import com.cus.metime.review.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Review.
 */
@RestController
@RequestMapping("/api")
public class ReviewResource {

    private final Logger log = LoggerFactory.getLogger(ReviewResource.class);

    private static final String ENTITY_NAME = "review";

    private final ReviewService reviewService;

    public ReviewResource(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * POST  /reviews : Create a new review.
     *
     * @param review the review to create
     * @return the ResponseEntity with status 201 (Created) and with body the new review, or with status 400 (Bad Request) if the review has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/reviews")
    @Timed
    public ResponseEntity<Review> createReview(@RequestBody Review review) throws URISyntaxException {
        log.debug("REST request to save Review : {}", review);
        if (review.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new review cannot already have an ID")).body(null);
        }
        Review result = reviewService.save(review);
        return ResponseEntity.created(new URI("/api/reviews/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /reviews : Updates an existing review.
     *
     * @param review the review to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated review,
     * or with status 400 (Bad Request) if the review is not valid,
     * or with status 500 (Internal Server Error) if the review couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/reviews")
    @Timed
    public ResponseEntity<Review> updateReview(@RequestBody Review review) throws URISyntaxException {
        log.debug("REST request to update Review : {}", review);
        if (review.getId() == null) {
            return createReview(review);
        }
        Review result = reviewService.save(review);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, review.getId().toString()))
            .body(result);
    }

    /**
     * GET  /reviews : get all the reviews.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of reviews in body
     */
    @GetMapping("/reviews")
    @Timed
    public ResponseEntity<List<Review>> getAllReviews(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Reviews");
        Page<Review> page = reviewService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/reviews");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /reviews/:id : get the "id" review.
     *
     * @param id the id of the review to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the review, or with status 404 (Not Found)
     */
    @GetMapping("/reviews/{id}")
    @Timed
    public ResponseEntity<Review> getReview(@PathVariable Long id) {
        log.debug("REST request to get Review : {}", id);
        Review review = reviewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(review));
    }

    /**
     * DELETE  /reviews/:id : delete the "id" review.
     *
     * @param id the id of the review to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/reviews/{id}")
    @Timed
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        log.debug("REST request to delete Review : {}", id);
        reviewService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
