package com.example.rentaherramientas.controllers;

import com.example.rentaherramientas.dto.BookingRequest;
import com.example.rentaherramientas.models.Booking;
import com.example.rentaherramientas.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // 1. Ver todas las reservas
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.listarTodas();
    }

    // 2. Crear una nueva reserva
    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {
        try {
            Booking nuevaReserva = bookingService.crearReserva(
                    request.getUserId(),
                    request.getToolId(),
                    request.getStartDate(),
                    request.getEndDate()
            );
            return ResponseEntity.ok(nuevaReserva);
        } catch (RuntimeException e) {
            // (ej: fechas mal puestas),error 400
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}