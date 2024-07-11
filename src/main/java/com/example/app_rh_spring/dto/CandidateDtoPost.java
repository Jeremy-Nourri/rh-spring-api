package com.example.app_rh_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateDtoPost {
    private String name;
    private long identificationNumber;
    private String address;
    private String phone;
    private String email;
    private String birthDate;
    private int rating;
    private String observation;
    private String skill;
    private String technicalArea;
    private String jobInterviewDate;
}
