package com.godoc.service.hospital;

import com.godoc.service.hospital.dto.RegisterHospitalRequest;
import com.godoc.service.hospital.dto.RegisterHospitalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    HospitalService hospitalService;

    @GetMapping("/hello")
    public String getConnection() {
        return "Hello world";
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterHospitalResponse> registerHospital(@Valid RegisterHospitalRequest request) throws Exception {
        RegisterHospitalResponse response =  hospitalService.registerHospital(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
