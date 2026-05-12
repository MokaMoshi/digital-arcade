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

import com.digital_arcade_spring.digital_arcade.DTO.RolDTO;
import com.digital_arcade_spring.digital_arcade.model.Rol;
import com.digital_arcade_spring.digital_arcade.service.RolService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearRol(@Valid @RequestBody RolDTO dto) {
        Rol nuevo = rolService.crearRol(dto);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Rol creado exitosamente");
        response.put("id", nuevo.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
