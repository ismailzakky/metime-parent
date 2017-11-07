package com.cus.metime.review.service;

import com.cus.metime.review.dto.ReviewDTO;
import java.util.List;
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
    ReviewDTO save(ReviewDTO review);

    /**
     *  Get all the reviews.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ReviewDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" review.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ReviewDTO findOne(Long id);

    /**
     *  Delete the "id" review.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
    
    List<ReviewDTO> findAllReviewsBySegment(String segment1, int limit, int offset);
}
