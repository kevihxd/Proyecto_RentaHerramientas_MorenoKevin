package com.example.rentaherramientas.repositories;

import com.example.rentaherramientas.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}