package com.example.mvc.controller;

import com.example.mvc.entity.Employee;
import com.example.mvc.service.EmployeeOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    // S (Responsabilidad Única, Single Responsibility): atiende las peticiones web y prepara
    // el modelo de vista; delega las reglas de empleados y la persistencia al servicio.
    // D (Inversión de Dependencias, Dependency Inversion): recibe la interfaz de casos de uso,
    // no su implementación concreta, reduciendo el acoplamiento con los detalles.
    private final EmployeeOperations employeeService;

    @GetMapping
    public String listEmployees(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String position,
            Model model) {
        model.addAttribute("employees", employeeService.getFilteredEmployees(department, position).stream()
                .sorted(Comparator.comparing(Employee::getName, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)))
                .toList());
        model.addAttribute("employee", new Employee());
        model.addAttribute("departmentFilter", department);
        model.addAttribute("positionFilter", position);
        return "employees";
    }

    @PostMapping
    public String createEmployee(
            @ModelAttribute("employee") Employee employee,
            RedirectAttributes redirectAttributes) {
        employeeService.createEmployee(employee);
        redirectAttributes.addFlashAttribute("successMessage", "Empleado creado correctamente.");
        return "redirect:/employees";
    }
}
