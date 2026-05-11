package com.digital_arcade_spring.digital_arcade.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 150, message = "El nombre debe tener entre 3 y 150 caracteres")
    @Column(nullable = false, length = 150)
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.1", message = "El precio debe ser mayor a 0")
    @Column(nullable = false)
    private BigDecimal precio;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(nullable = false)
    private Integer stock;

    // Relación con sus imágenes
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private java.util.List<Imagen> imagenes;

    // Relación hacia el puente de Categorías
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private java.util.List<Categorias> categoriasPuente;

    // Relación hacia el puente transaccional (Items)
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private java.util.List<Items> itemsPuente;
}