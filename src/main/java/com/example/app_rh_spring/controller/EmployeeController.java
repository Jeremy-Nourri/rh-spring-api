package com.example.app_rh_spring.controller;

import com.example.app_rh_spring.dto.EmployeeDotPost;
import com.example.app_rh_spring.dto.EmployeeDtoGet;
import com.example.app_rh_spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDtoGet>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDtoGet> getEmployeeById(@PathVariable("id") int id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDtoGet> postNewEmployee(@Validated @RequestBody EmployeeDotPost employeeDtoPost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employeeDtoPost));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDtoGet> putEmployee(@Validated @PathVariable("id") int id, @RequestBody EmployeeDotPost employeeDtoPost) {
        return ResponseEntity.ok(employeeService.update(id, employeeDtoPost));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable("id") int id) {
        return ResponseEntity.ok(employeeService.deleteById(id));
    }
}
