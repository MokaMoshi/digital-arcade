package com.digital_arcade_spring.digital_arcade.controller;

import com.digital_arcade_spring.digital_arcade.dto.ImagenDTO;
import com.digital_arcade_spring.digital_arcade.model.Imagen;
import com.digital_arcade_spring.digital_arcade.service.ImagenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/imagenes")
public class ImagenController {

    @Autowired
    private ImagenService imagenService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> guardarImagen(@Valid @RequestBody ImagenDTO imagenDTO) {
        Imagen img = imagenService.guardarImagen(imagenDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Registro de imagen guardado");
        response.put("url", img.getUrl());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}