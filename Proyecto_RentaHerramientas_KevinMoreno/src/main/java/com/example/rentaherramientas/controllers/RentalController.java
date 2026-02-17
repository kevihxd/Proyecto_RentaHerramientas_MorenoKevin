package com.example.rentaherramientas.controllers;

import com.example.rentaherramientas.models.*;
import com.example.rentaherramientas.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired private RentalRepository rentalRepository;
    @Autowired private ToolRepository toolRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private InvoiceRepository invoiceRepository;

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @GetMapping("/my-rentals")
    public List<Rental> getMyRentals() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User client = userRepository.findByUsername(username).orElseThrow();
        return rentalRepository.findByClient(client);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createRental(@RequestBody Map<String, Object> payload) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User client = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

            if (payload.get("toolId") == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "ID de herramienta no proporcionado"));
            }

            Long toolId = Long.valueOf(payload.get("toolId").toString());
            Tool tool = toolRepository.findById(toolId)
                    .orElseThrow(() -> new RuntimeException("Herramienta no encontrada"));

            if (tool.getStock() <= 0) {
                return ResponseEntity.badRequest().body(Map.of("message", "No hay stock disponible"));
            }

            Rental rental = new Rental();
            rental.setClient(client);
            rental.setTool(tool);
            rental.setStartDate(LocalDate.now());
            rental.setEndDate(LocalDate.now().plusDays(1));
            rental.setTotalCost(tool.getPricePerDay().doubleValue());
            rental.setStatus("ACTIVO");
            rentalRepository.save(rental);

            tool.setStock(tool.getStock() - 1);
            if (tool.getStock() == 0) tool.setStatus("AGOTADO");
            toolRepository.save(tool);

            Invoice invoice = new Invoice();
            invoice.setRental(rental);
            invoice.setTotalAmount(rental.getTotalCost());
            invoice.setIssueDate(LocalDateTime.now());
            invoice.setInvoiceNumber("INV-" + System.currentTimeMillis());
            invoice.setPaymentMethod("LINEA");
            invoiceRepository.save(invoice);
 
            return ResponseEntity.ok(Map.of(
                    "message", "Alquiler exitoso",
                    "invoiceNumber", invoice.getInvoiceNumber(),
                    "totalCost", rental.getTotalCost(),
                    "clientEmail", client.getEmail() != null ? client.getEmail() : "cliente@email.com"
            ));

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}