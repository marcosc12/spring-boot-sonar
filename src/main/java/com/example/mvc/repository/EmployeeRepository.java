package com.example.mvc.repository;

import com.example.mvc.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartmentAndPosition(String department, String position);
    List<Employee> findByDepartment(String department);
    List<Employee> findByPosition(String position);
}
