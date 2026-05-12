package com.digital_arcade_spring.digital_arcade.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "juegos")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre del juego no puede estar vacío")
    @Column(unique = true, length = 100)
    private String titulo;

    @NotBlank(message = "La descripción del juego no puede estar vacía")
    @Column(length = 255, nullable = false)
    private String descripcion;

    @NotBlank(message = "La fecha de lanzamiento no puede estar vacía")
    @Column(nullable = false, length = 20)
    private String fechaLanzamiento;

    @NotBlank(message = "El desarrollador del juego no puede estar vacío")
    @Column(nullable = false, length = 100)
    private String desarrollador;

    @OneToMany(mappedBy = "juego", cascade = CascadeType.ALL)
    private java.util.List<Imagen> imagenes;

}
