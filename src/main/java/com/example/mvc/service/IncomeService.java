package com.example.mvc.service;

import com.example.mvc.entity.Income;

/**
 * Contrato de aplicación para las operaciones de ingresos.
 * I (Segregación de Interfaces, Interface Segregation): limita las dependencias de los
 * consumidores a los casos CRUD necesarios para administrar ingresos.
 * L (Sustitución de Liskov, Liskov Substitution): las implementaciones deben respetar el
 * contrato CRUD compartido para Income.
 */
public interface IncomeService extends CrudService<Income> {
}
