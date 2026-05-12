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

import com.digital_arcade_spring.digital_arcade.DTO.TransaccionDTO;
import com.digital_arcade_spring.digital_arcade.model.Transaccion;
import com.digital_arcade_spring.digital_arcade.service.TransaccionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/procesar")
    public ResponseEntity<Map<String, Object>> procesar(@Valid @RequestBody TransaccionDTO transaccionDTO) {
        Transaccion t = transaccionService.procesarTransaccion(transaccionDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Transacción procesada correctamente");
        response.put("folio", t.getId());
        response.put("total_pagado", t.getTotal());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
