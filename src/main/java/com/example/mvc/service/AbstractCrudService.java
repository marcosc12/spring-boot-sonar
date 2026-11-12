package com.example.mvc.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

/**
 * Implementa el flujo CRUD transaccional compartido para un tipo de entidad.
 * S (Responsabilidad Única, Single Responsibility): centraliza las operaciones CRUD mediante
 * repositorio y su comportamiento transaccional.
 * O (Abierto/Cerrado, Open/Closed): se pueden agregar módulos con servicios y repositorios
 * tipados sin modificar esta implementación común ya probada.
 * L (Sustitución de Liskov, Liskov Substitution): las subclases respetan entradas, salidas y
 * expectativas de error de CrudService, por lo que pueden usarse mediante ese contrato.
 * D (Inversión de Dependencias, Dependency Inversion): utiliza la abstracción de repositorio
 * de Spring Data, no un adaptador de persistencia concreto.
 */
@Transactional(readOnly = true)
public abstract class AbstractCrudService<T> implements CrudService<T> {

    private final JpaRepository<T, Long> repository;

    protected AbstractCrudService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No record found with id " + id));
    }

    @Override
    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
