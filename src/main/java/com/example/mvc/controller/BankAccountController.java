package com.example.mvc.controller;

import com.example.mvc.entity.BankAccount;
import com.example.mvc.service.BankAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bank-accounts")
public class BankAccountController {
    // S (Responsabilidad Única, Single Responsibility): convierte las peticiones HTTP de cuentas
    // bancarias en modelos de vista y redirecciones; las reglas y la persistencia quedan fuera.
    // D (Inversión de Dependencias, Dependency Inversion): depende del contrato
    // BankAccountService, por lo que se pueden cambiar el servicio o la persistencia sin acoplar
    // este adaptador web a sus implementaciones.
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", bankAccountService.findAll());
        model.addAttribute("form", new BankAccount());
        return "bank-accounts";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("items", bankAccountService.findAll());
        model.addAttribute("form", bankAccountService.findById(id));
        return "bank-accounts";
    }

    @PostMapping
    public String create(@ModelAttribute("form") BankAccount bankAccount) {
        bankAccountService.save(bankAccount);
        return "redirect:/bank-accounts";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        bankAccountService.deleteById(id);
        return "redirect:/bank-accounts";
    }
}
