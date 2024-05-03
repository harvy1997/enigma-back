package com.engima.enigmaback.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PrestamoDto implements Serializable {
    private Integer idPrestamo;
    private Date fechaInicio;
    private Date fechaMaximaEntrega;
    private Date fechaEntrega;
    private Integer idLibro;
    private String nombreLibro;
    private String isbn;
}
