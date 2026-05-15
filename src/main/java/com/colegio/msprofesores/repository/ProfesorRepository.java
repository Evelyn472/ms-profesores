package com.colegio.msprofesores.repository;

import com.colegio.msprofesores.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    
    // Método extra por si necesitamos buscar a un profe por su correo
    Optional<Profesor> findByEmail(String email);
}