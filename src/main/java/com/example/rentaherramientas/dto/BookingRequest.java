package com.example.rentaherramientas.dto;

import lombok.Data;
import java.time.LocalDate;

@Data // Lombok genera los Getters y Setters automáticamente
public class BookingRequest {
    private Long userId;
    private Long toolId;
    private LocalDate startDate;
    private LocalDate endDate;
}