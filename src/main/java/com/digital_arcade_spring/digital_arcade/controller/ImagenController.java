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

import com.digital_arcade_spring.digital_arcade.DTO.ImagenDTO;
import com.digital_arcade_spring.digital_arcade.model.Imagen;
import com.digital_arcade_spring.digital_arcade.service.ImagenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/imagenes")
public class ImagenController {

    @Autowired
    private ImagenService imagenService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> guardarImagen(@Valid @RequestBody ImagenDTO imagenDTO) {
        Imagen img = imagenService.agregarImagen(imagenDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Registro de imagen guardado");
        response.put("url", img.getUrl());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}