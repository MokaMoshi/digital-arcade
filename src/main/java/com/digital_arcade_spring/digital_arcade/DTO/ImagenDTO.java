package com.digital_arcade_spring.digital_arcade.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagenDTO {

    // Solo uno de los dos: ítem de tienda o juego del catálogo
    private Integer itemId;
    private Integer juegoId;

    @NotBlank(message = "La URL de la imagen es obligatoria")
    private String url;

    private String altText;
}
