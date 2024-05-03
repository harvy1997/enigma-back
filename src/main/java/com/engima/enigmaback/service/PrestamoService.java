package com.engima.enigmaback.service;

import com.engima.enigmaback.Dto.PrestamoDto;
import com.engima.enigmaback.Dto.PrestamoRequestDto;
import com.engima.enigmaback.Dto.PrestamoResponseDto;
import com.engima.enigmaback.entities.Prestamo;
import com.engima.enigmaback.exceptions.ApiException;

import java.util.List;
import java.util.Optional;

public interface PrestamoService extends GenericService<Prestamo, Integer>{
    public PrestamoResponseDto update(PrestamoRequestDto prestamoRequestDto) throws ApiException;

    public List<PrestamoDto> findByIdUsuario(String identificadorUsuario) throws ApiException;
}