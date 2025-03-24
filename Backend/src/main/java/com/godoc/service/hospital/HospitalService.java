package com.godoc.service.hospital;

import com.godoc.service.hospital.dto.RegisterHospitalRequest;
import com.godoc.service.hospital.dto.RegisterHospitalResponse;

public interface HospitalService {

    RegisterHospitalResponse registerHospital(RegisterHospitalRequest request) throws Exception;

}
