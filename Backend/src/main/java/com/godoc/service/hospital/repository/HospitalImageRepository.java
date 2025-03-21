package com.godoc.service.hospital.repository;

import com.godoc.service.hospital.entity.HospitalImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalImageRepository extends JpaRepository<HospitalImage, Long> {

}
