package com.example.mvc.controller;

import com.example.mvc.dto.EmployeeDTO;
import com.example.mvc.entity.Employee;
import com.example.mvc.service.EmployeeOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/employees")
@RequiredArgsConstructor
public class EmployeeApiController {

    // S (Responsabilidad Única, Single Responsibility): adapta peticiones y respuestas HTTP/JSON;
    // no contiene reglas de empleados ni acceso directo a la base de datos.
    // D (Inversión de Dependencias, Dependency Inversion): delega las operaciones mediante
    // el contrato de casos de uso de empleados, no mediante una clase concreta.
    private final EmployeeOperations employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String position) {
        return ResponseEntity.ok(employeeService.getFilteredEmployees(department, position));
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());

        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }
}
