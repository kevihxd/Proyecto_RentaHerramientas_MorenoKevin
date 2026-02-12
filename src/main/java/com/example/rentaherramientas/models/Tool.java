package com.example.rentaherramientas.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Tools")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "price_per_day", nullable = false)
    private BigDecimal pricePerDay;

    @Column(nullable = false)
    private Integer stock;

    private String status; //  "DISPONIBLE", "RENTADA", "MANTENIMIENTO"

    @Column(name = "image_url")
    private String imageUrl;

    // Relación ManyToOne: Muchas herramientas pertenecen a un Proveedor (User)
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private User provider;
}