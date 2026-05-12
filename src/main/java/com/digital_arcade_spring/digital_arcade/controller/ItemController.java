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

import com.digital_arcade_spring.digital_arcade.DTO.ItemDTO;
import com.digital_arcade_spring.digital_arcade.model.Item;
import com.digital_arcade_spring.digital_arcade.service.ItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearItem(@Valid @RequestBody ItemDTO itemDTO) {
        Item nuevo = itemService.crearItemComunidad(itemDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Ítem registrado exitosamente");
        response.put("id", nuevo.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
