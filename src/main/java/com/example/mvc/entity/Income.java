package com.example.mvc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Modelo de persistencia de ingresos, con los datos que corresponden a un registro de ingreso.
 * S (Responsabilidad Única, Single Responsibility): representa y almacena los datos del ingreso;
 * el formato de presentación y los flujos financieros pertenecen a sus respectivas capas.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String category;
    private BigDecimal amount;
    private LocalDate receivedOn;
    private String bankAccount;
}
