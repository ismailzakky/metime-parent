package com.cus.metime.promosi.repository;

import com.cus.metime.promosi.domain.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


/**
 * Spring Data JPA repository for the Promo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PromoRepository extends JpaRepository<Promo, Long> {

    @Query("SELECT p FROM Promo p WHERE (:date BETWEEN p.validityPeriod.startDate AND p.validityPeriod.endDate) AND p.segment = :segment ")
    List<Promo> findByStartDateAfterAndEndDateBefore(@Param("date") LocalDate date, @Param("segment") String segment);


}
