package com.digital_arcade_spring.digital_arcade.model;

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
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre del rol no puede estar vacío")
    @Column(unique = true, length = 50)
    private String nombre;

    @NotBlank(message = "La descripción del rol no puede estar vacía")
    @Column(unique = true, length = 255)
    private String descripcion;

    @OneToMany(mappedBy = "rol")
    private java.util.List<Usuario> usuarios;
}
