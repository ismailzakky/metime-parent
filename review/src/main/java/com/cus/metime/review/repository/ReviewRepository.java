package com.cus.metime.review.repository;

import com.cus.metime.review.domain.Review;
import java.util.List;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


/**
 * Spring Data JPA repository for the Review entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = 
            "SELECT (sum(rv.rate_number)/count(rv.rate_number)) " +
            "FROM (" +
            "SELECT (CASE " +
            "        WHEN rate = 'VERY_POOR' THEN 1 " +
            "        WHEN rate = 'POOR' THEN 2 " +
            "        WHEN rate = 'NORMAL' THEN 3 " +
            "        WHEN rate = 'GOOD' THEN 4 " +
            "        WHEN rate = 'VERY_GOOD' THEN 5 " +
            "        ELSE 0 END) rate_number " +
            "FROM review " +
            "WHERE segment_1 = :segment1) rv", nativeQuery = true)
    public Integer rateOfReview(@Param("segment1") String segment1);
    
    @Query(value = 
            "SELECT rv1.* FROM " +
            "    (SELECT id, SUM(helpfull_rate) rate " +
            "    FROM " +
            "    (SELECT rv.id, " +
            "           (CASE " +
            "            WHEN helpfull_category = 'HELPFULL' THEN 1 " +
            "            WHEN helpfull_category = 'UNHELPFULL' THEN -1 " +
            "            WHEN helpfull_category = 'INAPPROPRIATE' THEN -2 " +
            "            WHEN helpfull_category = 'SPAM' THEN -3 " +
            "            ELSE 0 END) helpfull_rate " +
            "    FROM review rv " +
            "    LEFT JOIN help_full hf ON hf.review_id = rv.id) rv " +
            "    GROUP BY id) rv " +
            "    JOIN review rv1 ON rv1.id = rv.id " +
            "WHERE segment_1 = :segment1 " +
            "ORDER BY rv.rate DESC " +
            "LIMIT :limit OFFSET :offset", nativeQuery = true)
    public List<Review> findAllReview(@Param("segment1") String segment1, @Param("limit") int limit, @Param("offset") int offset);
}