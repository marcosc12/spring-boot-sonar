package com.example.mvc.controller;

import com.example.mvc.entity.Income;
import com.example.mvc.service.IncomeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/incomes")
public class IncomeController {
    // S (Responsabilidad Única, Single Responsibility): convierte las peticiones HTTP de
    // ingresos en modelos de vista y redirecciones; las reglas financieras y la persistencia
    // quedan fuera del controlador.
    // D (Inversión de Dependencias, Dependency Inversion): depende del contrato IncomeService,
    // de modo que la implementación del servicio o de la persistencia puede cambiar sin acoplar
    // este adaptador web a esos detalles.
    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", incomeService.findAll());
        model.addAttribute("form", new Income());
        return "incomes";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("items", incomeService.findAll());
        model.addAttribute("form", incomeService.findById(id));
        return "incomes";
    }

    @PostMapping
    public String create(@ModelAttribute("form") Income income) {
        incomeService.save(income);
        return "redirect:/incomes";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        incomeService.deleteById(id);
        return "redirect:/incomes";
    }
}
