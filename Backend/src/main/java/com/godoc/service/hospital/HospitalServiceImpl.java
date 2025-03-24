package com.godoc.service.hospital;

import com.godoc.service.common.S3Utils;
import com.godoc.service.credentials.Role;
import com.godoc.service.credentials.entity.Credentials;
import com.godoc.service.credentials.repository.CredentialsRepository;
import com.godoc.service.hospital.dto.RegisterHospitalRequest;
import com.godoc.service.hospital.dto.RegisterHospitalResponse;
import com.godoc.service.hospital.entity.Hospital;
import com.godoc.service.hospital.entity.HospitalImage;
import com.godoc.service.hospital.repository.HospitalImageRepository;
import com.godoc.service.hospital.repository.HospitalRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Service
public class HospitalServiceImpl implements HospitalService{

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private HospitalImageRepository hospitalImageRepository;

    @Autowired
    private CredentialsRepository credentialsRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Override
    public RegisterHospitalResponse registerHospital(RegisterHospitalRequest request) throws Exception {

        Credentials credentials = new Credentials();
        credentials.setUsername(request.getEmail());
        credentials.setPassword(passwordEncoder.encode(request.getPassword()));
        credentials.setRoles(
                Arrays.stream(Role.values())
                        .map(Enum::name)
                        .toList()
        );
        credentialsRepository.save(credentials);

        Hospital hospital = new Hospital();
        hospital.setName(request.getName());
        hospital.setEmail(request.getEmail());
        hospital.setCredentialsId(credentials.getId());
        hospital.setMotto(request.getMotto());
        hospital.setDescription(request.getDescription());
        hospital.setFounders(request.getFounders());
        hospital.setFoundationDate(request.getFoundationDate());
        hospitalRepository.save(hospital);

        String basePath = hospital.getId().toString() + "/";
        if (request.getLogo() != null) {
            String path = basePath + "/logo/" + request.getLogo().getOriginalFilename();
            hospital.setLogo(S3Utils.uploadMultipartFileToS3(path, request.getLogo()));
        }
        if (CollectionUtils.isNotEmpty(request.getImages())) {
            for (MultipartFile image : request.getImages()) {
                HospitalImage hospitalImage = new HospitalImage();

                hospitalImage.setHospitalId(hospital.getId());
                String path = basePath + "/images/" + image.getOriginalFilename();
                hospitalImage.setS3Url(S3Utils.uploadMultipartFileToS3(path, image));

                hospitalImageRepository.save(hospitalImage);
            }
        }
        hospitalRepository.save(hospital);

        RegisterHospitalResponse response = new RegisterHospitalResponse();
        response.setHospitalId(hospital.getId());
        response.setHospitalName(hospital.getName());
        response.setEmail(hospital.getEmail());

        return response;
    }
}
