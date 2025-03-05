package com.godoc.service.hospital.dto;

import lombok.Data;

@Data
public class RegisterHospitalResponse {
    private Long hospitalId;
    private String hospitalName;
    private String email;
}
