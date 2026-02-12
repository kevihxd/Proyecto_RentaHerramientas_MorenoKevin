package com.example.rentaherramientas.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "rentals")
@Data
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @ManyToOne
    @JoinColumn(name = "tool_id", nullable = false)
    private Tool tool;

    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalCost;
    private String status; // "ACTIVO", "FINALIZADO", "CANCELADO"

    // Eliminamos el método manual que recibía BigDecimal para evitar conflictos con Lombok
}