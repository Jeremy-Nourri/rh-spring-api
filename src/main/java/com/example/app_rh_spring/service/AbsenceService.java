package com.example.app_rh_spring.service;

import com.example.app_rh_spring.dto.AbsenceDtoGet;
import com.example.app_rh_spring.dto.AbsenceDtoPost;
import com.example.app_rh_spring.dto.EmployeeDtoGet;
import com.example.app_rh_spring.entity.Absence;
import com.example.app_rh_spring.exception.NotFoundException;
import com.example.app_rh_spring.repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class AbsenceService {

    @Autowired
    private AbsenceRepository absenceRepository;

    private EmployeeService employeeService;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public AbsenceDtoGet convertToDto(Absence absence) {
        return AbsenceDtoGet.builder()
                .id(absence.getId())
                .date(absence.getDate())
                .reason(absence.getReason())
                .employeeId(absence.getEmployee().getId())
                .build();

    }

    public AbsenceDtoGet create(AbsenceDtoPost absenceDtoPost) {
        EmployeeDtoGet employeeDtoGet = employeeService.findById(absenceDtoPost.getEmployeeId());

        return AbsenceDtoGet.builder()
                .date(LocalDate.parse(absenceDtoPost.getDate(), dateFormatter))
                .reason(absenceDtoPost.getReason())
                .employeeId(absenceDtoPost.getEmployeeId())
                .build();
    }

    public AbsenceDtoGet findById(int id) {
        Absence absence = absenceRepository.findById(id).orElseThrow(NotFoundException::new);
        return convertToDto(absence);
    }

    public void deleteById(int id) {
        AbsenceDtoGet absenceDtoGet = findById(id);
    }

}
