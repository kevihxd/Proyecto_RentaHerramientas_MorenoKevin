package com.example.rentaherramientas.repositories;

import com.example.rentaherramientas.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByStartDateBetween(LocalDate fechaInicio, LocalDate fechaFin);
}