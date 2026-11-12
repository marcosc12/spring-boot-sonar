package com.example.mvc.service;

import com.example.mvc.entity.Provider;

/**
 * Contrato de aplicación específico para proveedores.
 * L (Sustitución de Liskov, Liskov Substitution): toda implementación debe cumplir el contrato
 * CRUD heredado para Provider, de modo que pueda reemplazarse sin cambiar el comportamiento
 * que esperan sus consumidores.
 */
public interface ProviderService extends CrudService<Provider> {
}
