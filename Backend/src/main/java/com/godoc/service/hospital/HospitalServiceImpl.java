package com.godoc.service.hospital;

import com.godoc.service.hospital.dto.RegisterHospitalRequest;
import com.godoc.service.hospital.dto.RegisterHospitalResponse;
import com.godoc.service.hospital.entity.Hospital;
import com.godoc.service.hospital.repository.HospitalImageRepository;
import com.godoc.service.hospital.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalServiceImpl implements HospitalService{

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private HospitalImageRepository hospitalImageRepository;

    @Override
    public RegisterHospitalResponse registerHospital(RegisterHospitalRequest request) {

        Hospital hospital = new Hospital();
        hospital.setName(request.getName());
        hospital.setEmail(request.getEmail());


        return null;
    }
}
