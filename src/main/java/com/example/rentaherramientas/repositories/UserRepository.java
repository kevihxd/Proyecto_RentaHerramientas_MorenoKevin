package com.example.rentaherramientas.repositories;

import com.example.rentaherramientas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional; // <--- Importante este import

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // ESTA ES LA LÍNEA QUE TE FALTA:
    Optional<User> findByUsername(String username);

}