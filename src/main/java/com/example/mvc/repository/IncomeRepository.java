package com.example.mvc.repository;

import com.example.mvc.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Límite de persistencia de ingresos; los detalles de consulta y almacenamiento quedan fuera
 * de las capas web y de servicios.
 * S (Responsabilidad Única, Single Responsibility): este repositorio se ocupa únicamente de
 * persistir entidades Income.
 * I (Segregación de Interfaces, Interface Segregation): expone las operaciones Spring Data
 * correspondientes a esta entidad, sin mezclar ingresos con repositorios de otros módulos.
 * D (Inversión de Dependencias, Dependency Inversion): los servicios pueden depender de esta
 * interfaz en lugar de acoplarse a una implementación específica de base de datos.
 */
public interface IncomeRepository extends JpaRepository<Income, Long> {
}
