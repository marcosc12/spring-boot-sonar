package com.example.mvc.service;

import com.example.mvc.entity.Employee;
import com.example.mvc.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements EmployeeOperations {

    // D (Inversión de Dependencias, Dependency Inversion): el servicio usa el contrato del
    // repositorio, de manera que los detalles de persistencia pueden cambiar sin reescribir
    // las operaciones de negocio de empleados.
    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getFilteredEmployees(String department, String position) {
        if (department != null && position != null) {
            return employeeRepository.findByDepartmentAndPosition(department, position);
        } else if (department != null) {
            return employeeRepository.findByDepartment(department);
        } else if (position != null) {
            return employeeRepository.findByPosition(position);
        }
        return employeeRepository.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}