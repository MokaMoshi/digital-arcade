package com.digital_arcade_spring.digital_arcade.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionDTO {

    @NotNull(message = "El ID del usuario es obligatorio")
    private Integer usuarioId;

    @NotNull(message = "El método de pago es obligatorio")
    private Integer metodoPagoId;

    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.1", message = "El total debe ser mayor a 0")
    private BigDecimal total;

    @NotEmpty(message = "La transacción debe contener al menos un ítem")
    @Valid
    private List<ItemCompraDTO> items;
}