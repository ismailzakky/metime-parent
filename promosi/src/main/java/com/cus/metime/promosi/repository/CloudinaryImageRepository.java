package com.cus.metime.promosi.repository;

import com.cus.metime.promosi.domain.CloudinaryImage;
import com.cus.metime.promosi.domain.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface CloudinaryImageRepository extends JpaRepository<CloudinaryImage, String> {
}
