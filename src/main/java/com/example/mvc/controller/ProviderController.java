package com.example.mvc.controller;

import com.example.mvc.entity.Provider;
import com.example.mvc.service.ProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/providers")
public class ProviderController {
    // S (Responsabilidad Única, Single Responsibility): convierte las peticiones HTTP de
    // proveedores en modelos de vista y redirecciones; las reglas y la persistencia quedan fuera.
    // D (Inversión de Dependencias, Dependency Inversion): depende del contrato ProviderService,
    // por lo que se pueden cambiar el servicio o la persistencia sin acoplar este adaptador web.
    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", providerService.findAll());
        model.addAttribute("form", new Provider());
        return "providers";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("items", providerService.findAll());
        model.addAttribute("form", providerService.findById(id));
        return "providers";
    }

    @PostMapping
    public String create(@ModelAttribute("form") Provider provider) {
        providerService.save(provider);
        return "redirect:/providers";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        providerService.deleteById(id);
        return "redirect:/providers";
    }
}
