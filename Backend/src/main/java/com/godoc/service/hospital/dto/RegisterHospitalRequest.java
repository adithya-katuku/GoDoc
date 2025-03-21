package com.godoc.service.hospital.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.List;

@Data
public class RegisterHospitalRequest {
    @NotBlank(message = "Hospital name is required for registration.")
    @Size(max = 64, message = "Hospital name must not exceed 64 characters.")
    private String name;

    @Email(message = "Provide a valid email.")
    private String email;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 4, message = "Password must be 4 characters at least.")
    private String password;

    private MultipartFile logo;
    private List<MultipartFile> images;
    private String founders;
    private Calendar foundationDate;
    private String motto;
    private String description;
}
