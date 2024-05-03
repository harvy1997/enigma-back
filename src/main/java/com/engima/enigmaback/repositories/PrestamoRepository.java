package com.engima.enigmaback.repositories;

import com.engima.enigmaback.entities.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
    List<Prestamo> findByUsuario_identificacionUsuario(String identificacionUsuario);
}