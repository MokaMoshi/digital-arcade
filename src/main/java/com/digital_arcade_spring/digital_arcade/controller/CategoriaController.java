package com.digital_arcade_spring.digital_arcade.controller;

import com.digital_arcade_spring.digital_arcade.dto.CategoriaDTO;
import com.digital_arcade_spring.digital_arcade.model.Categoria;
import com.digital_arcade_spring.digital_arcade.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        Categoria nueva = categoriaService.crearCategoria(categoriaDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Categoría creada exitosamente");
        response.put("id", nueva.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}