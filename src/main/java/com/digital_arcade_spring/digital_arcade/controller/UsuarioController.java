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

import com.digital_arcade_spring.digital_arcade.DTO.UsuarioRegistroDTO;
import com.digital_arcade_spring.digital_arcade.model.Usuario;
import com.digital_arcade_spring.digital_arcade.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarUsuario(@Valid @RequestBody UsuarioRegistroDTO dto) {
        Usuario nuevo = usuarioService.registrarUsuario(dto);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Usuario registrado exitosamente");
        response.put("id", nuevo.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
