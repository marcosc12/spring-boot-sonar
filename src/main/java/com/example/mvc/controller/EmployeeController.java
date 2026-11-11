package com.example.mvc.controller;

import com.example.mvc.EmployeeDTO;
import com.example.mvc.entity.Employee;
import com.example.mvc.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String position) {
        List<Employee> employees = employeeService.getFilteredEmployees(department, position);
        return ResponseEntity.ok(employees);
    }

   @PostMapping
public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
    // Convertimos el DTO a la entidad antes de enviarla al servicio
    Employee employee = new Employee();
    employee.setName(employeeDTO.getName());
    employee.setEmail(employeeDTO.getEmail());
    employee.setPosition(employeeDTO.getPosition());
    employee.setSalary(employeeDTO.getSalary());

    Employee savedEmployee = employeeService.createEmployee(employee);
    return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
}
}