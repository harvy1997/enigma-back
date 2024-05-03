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
public class PrestamoResponseDto implements Serializable {
    private Integer id;
        private String fechaMaximaDevolucion;
}
