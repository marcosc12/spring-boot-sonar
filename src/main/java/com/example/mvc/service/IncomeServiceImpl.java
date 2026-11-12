package com.example.mvc.service;

import com.example.mvc.entity.Income;
import com.example.mvc.repository.IncomeRepository;
import org.springframework.stereotype.Service;

/**
 * Conecta los casos de uso de ingresos con su persistencia y reutiliza el flujo CRUD compartido.
 * S (Responsabilidad Única, Single Responsibility): esta clase solo conecta IncomeRepository
 * con dicho flujo.
 * O (Abierto/Cerrado, Open/Closed): agrega soporte para ingresos como una nueva implementación,
 * sin modificar el servicio CRUD genérico.
 * L (Sustitución de Liskov, Liskov Substitution): puede utilizarse donde se espera IncomeService.
 * D (Inversión de Dependencias, Dependency Inversion): recibe por constructor la interfaz
 * del repositorio.
 */
@Service
public class IncomeServiceImpl extends AbstractCrudService<Income> implements IncomeService {
    public IncomeServiceImpl(IncomeRepository repository) {
        super(repository);
    }
}
