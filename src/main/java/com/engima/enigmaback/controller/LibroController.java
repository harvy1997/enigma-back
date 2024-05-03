package com.engima.enigmaback.controller;


import com.engima.enigmaback.exceptions.ApiException;
import com.engima.enigmaback.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/libro")
@CrossOrigin(origins = "http://localhost:4200")
public class LibroController {

	@Autowired
	private LibroService libroService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@RequestParam("id") Integer id) throws ApiException {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.libroService.findById(id));
	}
	@GetMapping()
	public ResponseEntity<?> getAll() throws ApiException {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.libroService.findAll());
	}

	/*
	@GetMapping("/prestados")
	public ResponseEntity<?> getPrestado() throws ApiException {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.libroService.findById(id));
	}*/

}
