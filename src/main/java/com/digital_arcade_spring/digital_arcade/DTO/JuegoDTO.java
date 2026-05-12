package com.digital_arcade_spring.digital_arcade.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JuegoDTO {

    @NotBlank(message = "El título del juego es obligatorio")
    @Size(min = 3, max = 100, message = "El título debe tener entre 3 y 100 caracteres")
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 5, max = 255, message = "La descripción debe tener entre 5 y 255 caracteres")
    private String descripcion;

    @NotBlank(message = "La fecha de lanzamiento es obligatoria")
    @Size(max = 20, message = "La fecha no puede superar 20 caracteres")
    private String fechaLanzamiento;

    @NotBlank(message = "El desarrollador es obligatorio")
    @Size(max = 100, message = "El desarrollador no puede superar 100 caracteres")
    private String desarrollador;
}
