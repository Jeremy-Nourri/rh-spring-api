package com.example.app_rh_spring.service;

import com.example.app_rh_spring.dto.EmployeeDotPost;
import com.example.app_rh_spring.dto.EmployeeDtoGet;
import com.example.app_rh_spring.entity.Employee;
import com.example.app_rh_spring.exception.NotFoundException;
import com.example.app_rh_spring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public EmployeeDtoGet convertToDto(Employee employee){
        return EmployeeDtoGet.builder()
                .id(employee.getId())
                .name(employee.getName())
                .identificationNumber(employee.getIdentificationNumber())
                .address(employee.getAddress())
                .phone(employee.getPhone())
                .email(employee.getEmail())
                .birthDate(employee.getBirthDate())
                .contractStart(employee.getContractStart())
                .contractEnd(employee.getContractEnd())
                .occupation(employee.getOccupation())
                .salary(employee.getSalary())
                .observation(employee.getObservation())
                .build();
    }

    public EmployeeDtoGet createEmployee(EmployeeDotPost employerDtoPost) {
        Employee employee = Employee.builder()
                .email(employerDtoPost.getEmail())
                .name(employerDtoPost.getName())
                .phone(employerDtoPost.getPhone())
                .address(employerDtoPost.getAddress())
                .identificationNumber(employerDtoPost.getIdentificationNumber())
                .birthDate(LocalDate.parse(employerDtoPost.getBirthDate(), dateFormatter))
                .contractStart(LocalDate.parse(employerDtoPost.getContractStart(), dateFormatter))
                .contractEnd(LocalDate.parse(employerDtoPost.getContractEnd(), dateFormatter))
                .occupation(employerDtoPost.getOccupation())
                .password(employerDtoPost.getPassword())
                .isAdmin(employerDtoPost.isAdmin())
                .salary(employerDtoPost.getSalary())
                .observation(employerDtoPost.getObservation())
                .build();

        employeeRepository.save(employee);

        return convertToDto(employee);
    }

    public EmployeeDtoGet findById(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(NotFoundException::new);
        return convertToDto(employee);
    }

    public boolean deleteById(int id) {
        Employee employeeFound = employeeRepository.findById(id).orElseThrow(NotFoundException::new);
        employeeRepository.delete(employeeFound);
        Employee employee = employeeRepository.findById(id).orElse(null);
        return employee == null;
    }

    public List<EmployeeDtoGet> findAll() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        return employees.stream().map(this::convertToDto).toList();
    }

    public EmployeeDtoGet update(int id, EmployeeDotPost employeeDotPost) {
        Employee employee = employeeRepository.findById(id).orElseThrow(NotFoundException::new);
        employee.setEmail(employeeDotPost.getEmail());
        employee.setName(employeeDotPost.getName());
        employee.setPhone(employeeDotPost.getPhone());
        employee.setAddress(employeeDotPost.getAddress());
        employee.setIdentificationNumber(employeeDotPost.getIdentificationNumber());
        employee.setBirthDate(LocalDate.parse(employeeDotPost.getBirthDate(), dateFormatter));
        employee.setContractStart(LocalDate.parse(employeeDotPost.getContractStart(), dateFormatter));
        employee.setContractEnd(LocalDate.parse(employeeDotPost.getContractEnd(), dateFormatter));
        employee.setOccupation(employeeDotPost.getOccupation());
        employee.setPassword(employeeDotPost.getPassword());
        employee.setAdmin(employeeDotPost.isAdmin());
        employee.setSalary(employeeDotPost.getSalary());
        employee.setObservation(employeeDotPost.getObservation());
        employeeRepository.save(employee);
        return convertToDto(employee);
    }

}
