package com.example.rentaherramientas.repositories;

import com.example.rentaherramientas.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Se aplico est metodo para las reservas de un usuario especifico
    List<Booking> findByUserId(Long userId);
}