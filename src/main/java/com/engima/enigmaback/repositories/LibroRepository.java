package com.engima.enigmaback.repositories;

import com.engima.enigmaback.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
    public Libro findByIsbn(String isbn);



}