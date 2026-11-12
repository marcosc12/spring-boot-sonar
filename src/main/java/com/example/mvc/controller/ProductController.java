package com.example.mvc.controller;

import com.example.mvc.entity.Product;
import com.example.mvc.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
    // S (Responsabilidad Única, Single Responsibility): convierte las peticiones HTTP de
    // productos en modelos de vista y redirecciones; las reglas de inventario y persistencia quedan fuera.
    // D (Inversión de Dependencias, Dependency Inversion): depende del contrato ProductService,
    // por lo que se pueden cambiar el servicio o la persistencia sin acoplar este adaptador web.
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", productService.findAll());
        model.addAttribute("form", new Product());
        return "products";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("items", productService.findAll());
        model.addAttribute("form", productService.findById(id));
        return "products";
    }

    @PostMapping
    public String create(@ModelAttribute("form") Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}
