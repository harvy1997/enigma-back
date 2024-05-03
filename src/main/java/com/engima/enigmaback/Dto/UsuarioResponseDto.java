package com.engima.enigmaback.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponseDto {
    private Integer idUsuario;
    private String nombre;
    private String identificacionUsuario;
}
