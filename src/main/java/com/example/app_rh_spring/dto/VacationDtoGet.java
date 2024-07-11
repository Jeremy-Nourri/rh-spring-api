package com.example.app_rh_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacationDtoGet {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private int employeeId;
}