package com.example.app_rh_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDotPost {
    private String name;
    private long identificationNumber;
    private String address;
    private String phone;
    private String email;
    private String birthDate;
    private String contractStart;
    private String contractEnd;
    private String occupation;
    private String password;
    private boolean isAdmin;
    private double salary;
    private String observation;
}
