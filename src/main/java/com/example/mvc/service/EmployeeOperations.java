package com.example.mvc.service;

import com.example.mvc.entity.Employee;

import java.util.List;

/**
 * Casos de uso de empleados que necesitan los adaptadores MVC y REST.
 * I (Segregación de Interfaces, Interface Segregation): expone solo el listado, el filtrado
 * y la creación de empleados; los consumidores no dependen de operaciones de persistencia
 * ajenas a sus necesidades.
 */
public interface EmployeeOperations {
    List<Employee> getFilteredEmployees(String department, String position);

    Employee createEmployee(Employee employee);
}
