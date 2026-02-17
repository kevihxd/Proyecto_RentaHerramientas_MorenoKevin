package com.example.rentaherramientas.repositories;

import com.example.rentaherramientas.models.Rental; // Ajusta al nombre de tu paquete
import com.example.rentaherramientas.models.User;   // Ajusta al nombre de tu paquete
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByClient(User client);
}