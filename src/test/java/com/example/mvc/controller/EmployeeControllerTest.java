package com.example.mvc.controller;

import com.example.mvc.entity.Employee;
import com.example.mvc.service.EmployeeOperations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeOperations employeeService;

    @Test
    void listsEmployeesUsingTheThymeleafView() throws Exception {
        Employee ana = new Employee();
        ana.setName("Ana López");
        Employee carlos = new Employee();
        carlos.setName("Carlos Pérez");
        when(employeeService.getFilteredEmployees(isNull(), isNull())).thenReturn(List.of(carlos, ana));

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(view().name("employees"))
                .andExpect(model().attribute("employees", List.of(ana, carlos)))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Ana López")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Carlos Pérez")));
    }

    @Test
    void createsEmployeeAndRedirectsToTheDirectory() throws Exception {
        mockMvc.perform(post("/employees")
                        .param("name", "Ana López")
                        .param("email", "ana@example.com")
                        .param("department", "IT")
                        .param("position", "Developer")
                        .param("salary", "65000"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/employees"));

        verify(employeeService).createEmployee(org.mockito.ArgumentMatchers.argThat(employee ->
                "Ana López".equals(employee.getName())
                        && "ana@example.com".equals(employee.getEmail())
                        && "IT".equals(employee.getDepartment())
                        && "Developer".equals(employee.getPosition())
                        && Double.valueOf(65000).equals(employee.getSalary())));
    }
}
