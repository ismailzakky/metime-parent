package com.cus.metime.review.repository;

import com.cus.metime.review.domain.HelpFull;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the HelpFull entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HelpFullRepository extends JpaRepository<HelpFull, Long> {

}
