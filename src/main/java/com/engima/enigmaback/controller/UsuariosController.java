package com.engima.enigmaback.controller;


import com.engima.enigmaback.Dto.MensajeDto;
import com.engima.enigmaback.Dto.UsuarioRequestDto;
import com.engima.enigmaback.exceptions.ApiException;
import com.engima.enigmaback.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuariosController {
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") Integer id) throws ApiException {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioService.findById(id));
	}
	@GetMapping()
	public ResponseEntity<?> getAll() throws ApiException {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioService.findAll());
	}
	@PostMapping("/prestamo")
	public ResponseEntity<?> getB(@RequestBody UsuarioRequestDto usuarioRequestDto) throws ApiException {
		System.out.println(usuarioRequestDto.getFecha());
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		try{
			return ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioService.usuariosPrestamos(dateFormat.parse(usuarioRequestDto.getFecha()) ));
		}catch (ParseException exception){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDto(exception.toString()));
		}
	}
	
}
