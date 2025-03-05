package com.godoc.service.hospital.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class HospitalImage {
    @Id
    private Long id;
    private Long hospitalId;
    private String s3Url;
}
