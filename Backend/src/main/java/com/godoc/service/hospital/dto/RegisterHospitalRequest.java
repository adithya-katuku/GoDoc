package com.godoc.service.hospital.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.List;

@Data
public class RegisterHospitalRequest {
    private String name;
    private String email;
    private String password;
    private MultipartFile logo;
    private List<MultipartFile> images;
    private String founders;
    private Calendar foundationDate;
    private String motto;
    private String description;
}
