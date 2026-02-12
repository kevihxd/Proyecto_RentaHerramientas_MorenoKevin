package com.example.rentaherramientas.services;

import com.example.rentaherramientas.models.Booking;
import com.example.rentaherramientas.models.Tool;
import com.example.rentaherramientas.models.User;
import com.example.rentaherramientas.repositories.BookingRepository;
import com.example.rentaherramientas.repositories.ToolRepository;
import com.example.rentaherramientas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private UserRepository userRepository;

    // 1. Listar todas las reservas
    public List<Booking> listarTodas() {
        return bookingRepository.findAll();
    }

    // 2. Crear una nueva reserva con lógica de negocio
    public Booking crearReserva(Long userId, Long toolId, LocalDate fechaInicio, LocalDate fechaFin) {

        // A. Buscar el usuario y la herramienta en la BD
        User usuario = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Tool herramienta = toolRepository.findById(toolId)
                .orElseThrow(() -> new RuntimeException("Herramienta no encontrada"));

        // B. Validar disponibilidad (básico)
        if (!herramienta.getStatus().equals("DISPONIBLE")) {
            throw new RuntimeException("La herramienta no está disponible.");
        }

        // C. Calcular días de alquiler
        long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        if (dias <= 0) {
            throw new RuntimeException("La fecha de fin debe ser posterior a la de inicio");
        }

        // D. Calcular Precio Total: (Días * PrecioPorDía)
        BigDecimal total = herramienta.getPricePerDay().multiply(BigDecimal.valueOf(dias));

        // E. Crear y guardar la reserva
        Booking nuevaReserva = new Booking();
        nuevaReserva.setUser(usuario);
        nuevaReserva.setTool(herramienta);
        nuevaReserva.setStartDate(fechaInicio);
        nuevaReserva.setEndDate(fechaFin);
        nuevaReserva.setTotalPrice(total);
        nuevaReserva.setStatus("PENDIENTE"); // Estado inicial

        return bookingRepository.save(nuevaReserva);
    }
}