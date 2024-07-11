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
public class AbsenceDtoGet {
    private int id;
    private LocalDate date;
    private String reason;
    private int employeeId;
}
