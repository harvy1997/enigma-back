package com.engima.enigmaback.repositories;

import com.engima.enigmaback.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    public Usuario findByidentificacionUsuario(String identificacionUsuario);
    @Query("SELECT p.usuario FROM Prestamo p  " +
            "WHERE p.fechaMaximaEntrega > :fecha " +
            "AND p.fechaEntrega is null "
    )
    public List<Usuario> usuariosPrestamos(Date fecha);
}