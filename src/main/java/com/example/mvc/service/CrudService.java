package com.example.mvc.service;

import java.util.List;

/**
 * Contrato mínimo de operaciones CRUD compartido por los módulos de recursos.
 * I (Segregación de Interfaces, Interface Segregation): define capacidades explícitas y
 * cohesionadas para que los consumidores dependan de este contrato pequeño, no de una
 * interfaz de aplicación amplia con operaciones no relacionadas.
 */
public interface CrudService<T> {
    List<T> findAll();

    T findById(Long id);

    T save(T entity);

    void deleteById(Long id);
}
