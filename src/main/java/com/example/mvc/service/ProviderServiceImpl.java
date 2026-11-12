package com.example.mvc.service;

import com.example.mvc.entity.Provider;
import com.example.mvc.repository.ProviderRepository;
import org.springframework.stereotype.Service;

/**
 * Conecta la persistencia de proveedores con el flujo CRUD compartido.
 * O (Abierto/Cerrado, Open/Closed): agrega el módulo de proveedores mediante abstracciones
 * existentes, sin modificar AbstractCrudService.
 * L (Sustitución de Liskov, Liskov Substitution): conserva el contrato de ProviderService.
 * D (Inversión de Dependencias, Dependency Inversion): recibe ProviderRepository por constructor,
 * dependiendo de su abstracción y no de una implementación de base de datos.
 */
@Service
public class ProviderServiceImpl extends AbstractCrudService<Provider> implements ProviderService {
    public ProviderServiceImpl(ProviderRepository repository) {
        super(repository);
    }
}
