package com.engima.enigmaback.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_usuario")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TipoUsuario {

    @Id
    @Column(name = "id_tipo_usuario", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoUsuario;

    @Column(name = "nombre", nullable = false)
    private String nombre;

}
