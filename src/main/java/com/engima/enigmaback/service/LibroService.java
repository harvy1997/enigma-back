package com.engima.enigmaback.service;

import com.engima.enigmaback.entities.Libro;

public interface LibroService extends GenericService<Libro, Integer>{
    public Libro findByIsbn(String isbn);
}