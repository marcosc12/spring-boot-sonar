package com.example.mvc;

import com.example.mvc.entity.Employee;
import com.example.mvc.repository.EmployeeRepository;
import com.example.mvc.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testCreateEmployee() {
        Employee employee = new Employee(1L, "Juan Pérez", "juan@example.com", "IT", "Developer");
        
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee saved = employeeService.createEmployee(employee);

        assertEquals("Juan Pérez", saved.getName());
        assertEquals("IT", saved.getDepartment());
    }
}