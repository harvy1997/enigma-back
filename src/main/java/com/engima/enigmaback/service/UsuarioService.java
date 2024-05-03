package com.engima.enigmaback.service;

import com.engima.enigmaback.Dto.UsuarioResponseDto;
import com.engima.enigmaback.entities.Usuario;

import java.util.Date;
import java.util.List;

public interface UsuarioService extends GenericService<Usuario, Integer>{
    public Usuario findByIdentificacion(String identificacion);
    public List<UsuarioResponseDto> usuariosPrestamos(Date fecha);
}