package com.engima.enigmaback.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PrestamoRequestDto implements Serializable {
    private String identificacionUsuario;
    private String isbn;
    private Integer tipoUsuario;
}
