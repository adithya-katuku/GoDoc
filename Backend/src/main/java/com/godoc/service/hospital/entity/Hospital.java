package com.godoc.service.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;

@Entity
@Data
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_id_generator")
    @SequenceGenerator(name = "hospital_id_generator", allocationSize = 1, sequenceName = "hospital_id_seq")
    private Long id;
    private String name;
    private String email;
    private String password;
    private String logo;
    private String founders;
    private Calendar foundationDate;
    private String motto;
    private String description;
}
