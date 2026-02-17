package com.example.rentaherramientas.controllers;

import com.example.rentaherramientas.dto.BookingRequest;
import com.example.rentaherramientas.models.Booking;
import com.example.rentaherramientas.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.listarTodas();
    }

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
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<Booking>> filtrarReservas(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        List<Booking> reservas = bookingService.filtrarReservas(fechaInicio, fechaFin);
        return ResponseEntity.ok(reservas);
    }
}