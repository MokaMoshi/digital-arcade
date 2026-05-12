package com.digital_arcade_spring.digital_arcade.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital_arcade_spring.digital_arcade.DTO.JuegoDTO;
import com.digital_arcade_spring.digital_arcade.model.Juego;
import com.digital_arcade_spring.digital_arcade.service.JuegoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/juegos")
public class JuegoController {

    @Autowired
    private JuegoService juegoService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearJuego(@Valid @RequestBody JuegoDTO dto) {
        Juego nuevo = juegoService.crearJuego(dto);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Juego registrado exitosamente");
        response.put("id", nuevo.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
