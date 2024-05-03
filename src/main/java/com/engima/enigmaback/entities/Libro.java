package com.engima.enigmaback.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "libro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Libro {
    @Id
    @Column(name = "id_libro", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLibro;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "isbn", nullable = false,length = 10)
    private String isbn;
}
