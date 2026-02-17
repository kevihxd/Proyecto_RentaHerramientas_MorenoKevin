package com.example.rentaherramientas.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingRequest {
    private Long userId;
    private Long toolId;
    private LocalDate startDate;
    private LocalDate endDate;
}