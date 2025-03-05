package com.godoc.service.hospital;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HospitalController {

    @GetMapping("/hello")
    public String getConnection() {
        return "Hello world";
    }

}
