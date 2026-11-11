package com.example.mvc.service;

import com.example.mvc.entity.Employee;
import com.example.mvc.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

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

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}