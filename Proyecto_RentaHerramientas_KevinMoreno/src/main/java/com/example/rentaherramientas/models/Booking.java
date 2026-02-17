package com.example.rentaherramientas.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación: Una reserva pertenece a un Cliente
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Relación: Una reserva es para una Herramienta
    @ManyToOne
    @JoinColumn(name = "tool_id", nullable = false)
    private Tool tool;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private BigDecimal totalPrice;

    // ESTADO: PENDIENTE, APROBADA, RECHAZADA, FINALIZADA
    private String status;
}