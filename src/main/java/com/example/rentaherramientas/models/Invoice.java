package com.example.rentaherramientas.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String invoiceNumber; // Ejemplo: FAC-174000000

    private LocalDateTime issueDate;

    private Double totalAmount;

    private String paymentMethod; // "LINEA", "TRANSFERENCIA"

    // Relación OneToOne: Una factura pertenece exactamente a un alquiler
    @OneToOne
    @JoinColumn(name = "rental_id", nullable = false)
    private Rental rental;
}