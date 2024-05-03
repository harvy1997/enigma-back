package com.engima.enigmaback.controller;


import com.engima.enigmaback.Dto.MensajeDto;
import com.engima.enigmaback.Dto.PrestamoDto;
import com.engima.enigmaback.Dto.PrestamoRequestDto;
import com.engima.enigmaback.Dto.PrestamoResponseDto;
import com.engima.enigmaback.entities.Libro;
import com.engima.enigmaback.entities.Prestamo;
import com.engima.enigmaback.exceptions.ApiException;
import com.engima.enigmaback.service.LibroService;
import com.engima.enigmaback.service.PrestamoService;
import jakarta.websocket.server.PathParam;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prestamo")
@CrossOrigin(origins = "http://localhost:4200")
public class PrestamoController {

	@Autowired
	private PrestamoService prestamoService;
	@Autowired
	private LibroService libroService;

	@GetMapping("/{idPrestamo}")
	public ResponseEntity<?> getOne(@PathVariable("idPrestamo") Integer idPrestamo) {
		try {
			Prestamo prestamo=this.prestamoService.findById(idPrestamo);
			Libro libro= libroService.findById(prestamo.getLibro().getIdLibro());
			PrestamoDto prestamoDto=PrestamoDto.builder()
					.idPrestamo(prestamo.getIdPrestamo())
					.nombreLibro(libro.getNombre())
					.idLibro(libro.getIdLibro())
					.fechaMaximaEntrega(prestamo.getFechaMaximaEntrega())
					.fechaEntrega(prestamo.getFechaEntrega())
					.isbn(libro.getIsbn())
					.fechaInicio(prestamo.getFechaInicio())
					.build();
		return ResponseEntity.status(HttpStatus.CREATED).body(prestamoDto);
	}catch (ApiException apiException){
			return ResponseEntity.status(apiException.getCode()).body(new MensajeDto(apiException.getException()));
		}
	}
	@PostMapping
	public ResponseEntity<?> update(@RequestBody PrestamoRequestDto prestamoRequestDto) {
		try {
		PrestamoResponseDto prestamoResponseDto= this.prestamoService.update(prestamoRequestDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(prestamoResponseDto);
		}catch (ApiException apiException){
			return ResponseEntity.status(apiException.getCode()).body(new MensajeDto(apiException.getException()));
		}
	}
	@GetMapping("/usuario/{id}")
	public ResponseEntity<?> getByIdUsuario(@PathVariable("id") String idUsuario) {
		try {
			List<PrestamoDto> prestamoResponseDto= this.prestamoService.findByIdUsuario(idUsuario);
			return ResponseEntity.status(HttpStatus.CREATED).body(prestamoResponseDto);
		}catch (ApiException apiException){
			return ResponseEntity.status(apiException.getCode()).body(new MensajeDto(apiException.getException()));
		}
	}

}
