package com.cus.metime.review.service;

import com.cus.metime.review.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Review.
 */
public interface ReviewService {

    /**
     * Save a review.
     *
     * @param review the entity to save
     * @return the persisted entity
     */
    Review save(Review review);

    /**
     *  Get all the reviews.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Review> findAll(Pageable pageable);

    /**
     *  Get the "id" review.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Review findOne(Long id);

    /**
     *  Delete the "id" review.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
