package com.cus.metime.review.service.impl;

import com.cus.metime.review.service.ReviewService;
import com.cus.metime.review.domain.Review;
import com.cus.metime.review.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Review.
 */
@Service
@Transactional
public class ReviewServiceImpl implements ReviewService{

    private final Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Save a review.
     *
     * @param review the entity to save
     * @return the persisted entity
     */
    @Override
    public Review save(Review review) {
        log.debug("Request to save Review : {}", review);
        return reviewRepository.save(review);
    }

    /**
     *  Get all the reviews.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Review> findAll(Pageable pageable) {
        log.debug("Request to get all Reviews");
        return reviewRepository.findAll(pageable);
    }

    /**
     *  Get one review by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Review findOne(Long id) {
        log.debug("Request to get Review : {}", id);
        return reviewRepository.findOne(id);
    }

    /**
     *  Delete the  review by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Review : {}", id);
        reviewRepository.delete(id);
    }
}