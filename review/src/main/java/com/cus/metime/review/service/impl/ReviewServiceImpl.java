package com.cus.metime.review.service.impl;

import com.cus.metime.review.domain.CreationalDate;
import com.cus.metime.review.domain.Review;
import com.cus.metime.review.service.ReviewService;
import com.cus.metime.review.dto.ReviewDTO;
import com.cus.metime.review.dto.assembler.ReviewAssembler;
import com.cus.metime.review.repository.HelpFullRepository;
import com.cus.metime.review.repository.ReviewRepository;
import java.util.Date;
import java.util.List;
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
public class ReviewServiceImpl implements ReviewService {

    private final Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final ReviewRepository reviewRepository;
    private final HelpFullRepository helpFullRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, HelpFullRepository helpFullRepository) {
        this.reviewRepository = reviewRepository;
        this.helpFullRepository = helpFullRepository;
    }

    /**
     * Save a review.
     *
     * @param review the entity to save
     * @return the persisted entity
     */
    @Override
    public ReviewDTO save(ReviewDTO review) {
        CreationalDate creationalDate = new CreationalDate();
        if (review.getId() == null) {
            creationalDate.setCreatedAt(new Date());
            creationalDate.setCreatedBy(review.getCreationalDate().getCreatedBy());
            creationalDate.setModifiedAt(new Date());
            creationalDate.setModifiedBy(review.getCreationalDate().getCreatedBy());
        } else {
            ReviewDTO reviewExisting = findOne(review.getId());
            creationalDate.setCreatedAt(reviewExisting.getCreationalDate().getCreatedAt());
            creationalDate.setCreatedBy(reviewExisting.getCreationalDate().getCreatedBy());
            creationalDate.setModifiedAt(new Date());
            creationalDate.setModifiedBy(review.getCreationalDate().getModifiedBy());
        }
        review.setCreationalDate(creationalDate);
        log.debug("Request to save Review : {}", review);
        return new ReviewAssembler()
                .toDTO(reviewRepository.save(new ReviewAssembler().toDomain(review)));
    }

    /**
     * Get all the reviews.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ReviewDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Reviews");
        Page<ReviewDTO> reviews = new ReviewAssembler(reviewRepository).toDTOs(reviewRepository.findAll(pageable));
        return reviews;
    }

    /**
     * Get one review by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ReviewDTO findOne(Long id) {
        log.debug("Request to get Review : {}", id);
        Review review = reviewRepository.findOne(id);
        if (review == null) {
            review = new Review();
        }
        return new ReviewAssembler()
                .toDTO(review);
    }

    /**
     * Delete the review by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Review : {}", id);
        reviewRepository.delete(id);
    }

    @Override
    public List<ReviewDTO> findAllReviewsBySegment(String segment1, int limit, int offset) {
        log.debug("Request to get all Reviews By Segment: " + segment1);
        List<ReviewDTO> reviews = new ReviewAssembler(reviewRepository).toDTOs(reviewRepository.findAllReview(segment1, limit, offset));
        return reviews;
    }
    
    
}
