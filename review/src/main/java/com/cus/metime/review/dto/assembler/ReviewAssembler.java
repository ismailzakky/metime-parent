package com.cus.metime.review.dto.assembler;

import com.cus.metime.review.domain.Review;
import com.cus.metime.review.domain.ReviewBuilder;
import com.cus.metime.review.dto.ReviewDTO;
import com.cus.metime.review.dto.ReviewDTOBuilder;
import com.cus.metime.review.repository.ReviewRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Handoyo
 */
@Service
@Transactional
public class ReviewAssembler {

    private ReviewRepository reviewRepository;
    
    public ReviewAssembler(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ReviewAssembler() {
    }

    public ReviewDTO toDTO(Review review) {
        Integer rateOfReview = 0;
        if (reviewRepository != null) {
            rateOfReview = reviewRepository.rateOfReview(review.getSegment1());
        }
        return new ReviewDTOBuilder()
                .setId(review.getId())
                .setRate(review.getRate())
                .setComment(review.getComment())
                .setSegment1(review.getSegment1())
                .setCreationalDate(review.getCreationalDate())
                .setRateOfReview(rateOfReview)
                .createReviewDTO();
    }
    
    public Review toDomain(ReviewDTO reviewDTO) {
        return new ReviewBuilder()
                .setId(reviewDTO.getId())
                .setRate(reviewDTO.getRate())
                .setComment(reviewDTO.getComment())
                .setSegment1(reviewDTO.getSegment1())
                .setCreationalDate(reviewDTO.getCreationalDate())
                .createReview();
    }
    
    public Page<ReviewDTO> toDTOs(Page<Review> reviews) {
        Page<ReviewDTO> reviewDTOs = new Page<ReviewDTO>() {
            @Override
            public int getTotalPages() {
                return reviews.getTotalPages();
            }

            @Override
            public long getTotalElements() {
                return reviews.getTotalElements();
            }

            @Override
            public <S> Page<S> map(Converter<? super ReviewDTO, ? extends S> cnvrtr) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getNumber() {
                return reviews.getNumber();
            }

            @Override
            public int getSize() {
                return reviews.getSize();
            }

            @Override
            public int getNumberOfElements() {
                return reviews.getNumberOfElements();
            }

            @Override
            public List<ReviewDTO> getContent() {
                List<ReviewDTO> reviewDTOs = new ArrayList();
                for (Review review : reviews.getContent()) {
                    reviewDTOs.add(toDTO(review));
                }
                return reviewDTOs;
            }

            @Override
            public boolean hasContent() {
                return reviews.hasContent();
            }

            @Override
            public Sort getSort() {
                return reviews.getSort();
            }

            @Override
            public boolean isFirst() {
                return reviews.isFirst();
            }

            @Override
            public boolean isLast() {
                return reviews.isLast();
            }

            @Override
            public boolean hasNext() {
                return reviews.hasNext();
            }

            @Override
            public boolean hasPrevious() {
                return reviews.hasPrevious();
            }

            @Override
            public Pageable nextPageable() {
                return reviews.nextPageable();
            }

            @Override
            public Pageable previousPageable() {
                return reviews.previousPageable();
            }

            @Override
            public Iterator<ReviewDTO> iterator() {
                Collection<ReviewDTO> reviewDTOs = new ArrayList();
                while (reviews.hasNext()) {                    
                    reviewDTOs.add(toDTO(reviews.iterator().next()));
                }
                Iterator<ReviewDTO> iterator = reviewDTOs.iterator();
                return iterator;
            }
        };
        return reviewDTOs;
    }
    
    public List<ReviewDTO> toDTOs(List<Review> reviews) {
        List<ReviewDTO> reviewDTOs = new ArrayList();
        for (Review review : reviews) {
            reviewDTOs.add(toDTO(review));
        }
        return reviewDTOs;
    }
    
    public List<Review> toDomains(List<ReviewDTO> reviewDTOs) {
        List<Review> reviews = new ArrayList();
        for (ReviewDTO reviewDTO : reviewDTOs) {
            reviews.add(toDomain(reviewDTO));
        }
        return reviews;
    }
}
