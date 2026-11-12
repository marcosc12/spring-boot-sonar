package com.example.mvc.controller;

import com.example.mvc.entity.Expense;
import com.example.mvc.service.ExpenseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {
    // S (Responsabilidad Única, Single Responsibility): convierte las peticiones HTTP de gastos
    // en modelos de vista y redirecciones; las reglas financieras y la persistencia quedan fuera.
    // D (Inversión de Dependencias, Dependency Inversion): depende del contrato ExpenseService,
    // por lo que se pueden cambiar el servicio o la persistencia sin acoplar este adaptador web.
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", expenseService.findAll());
        model.addAttribute("form", new Expense());
        return "expenses";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("items", expenseService.findAll());
        model.addAttribute("form", expenseService.findById(id));
        return "expenses";
    }

    @PostMapping
    public String create(@ModelAttribute("form") Expense expense) {
        expenseService.save(expense);
        return "redirect:/expenses";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        expenseService.deleteById(id);
        return "redirect:/expenses";
    }
}
