package com.digital_arcade_spring.digital_arcade.controller;

import com.digital_arcade_spring.digital_arcade.dto.MetodoPagoDTO;
import com.digital_arcade_spring.digital_arcade.model.MetodoPago;
import com.digital_arcade_spring.digital_arcade.service.MetodoPagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/metodos-pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarMetodo(@Valid @RequestBody MetodoPagoDTO dto) {
        MetodoPago mp = metodoPagoService.crearMetodoPago(dto);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Método de pago habilitado");
        response.put("nombre", mp.getNombre());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}