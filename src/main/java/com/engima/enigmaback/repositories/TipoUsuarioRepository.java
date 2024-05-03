package com.engima.enigmaback.repositories;

import com.engima.enigmaback.entities.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {
}