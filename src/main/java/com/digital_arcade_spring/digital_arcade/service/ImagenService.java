package com.digital_arcade_spring.digital_arcade.service;

import com.digital_arcade_spring.digital_arcade.DTO.ImagenDTO;
import com.digital_arcade_spring.digital_arcade.model.Imagen;
import com.digital_arcade_spring.digital_arcade.model.Item;
import com.digital_arcade_spring.digital_arcade.model.Juego;
import com.digital_arcade_spring.digital_arcade.repository.ImagenRepository;
import com.digital_arcade_spring.digital_arcade.repository.ItemRepository;
import com.digital_arcade_spring.digital_arcade.repository.JuegoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    @Transactional
    public Imagen agregarImagen(ImagenDTO dto) {
        boolean tieneItem = dto.getItemId() != null;
        boolean tieneJuego = dto.getJuegoId() != null;

        if (tieneItem && tieneJuego) {
            throw new RuntimeException("Indica solo itemId o solo juegoId, no ambos");
        }
        if (!tieneItem && !tieneJuego) {
            throw new RuntimeException("Debes enviar itemId (tienda) o juegoId (catálogo)");
        }

        Imagen img = new Imagen();
        img.setUrl(dto.getUrl());
        img.setAltText(dto.getAltText());

        if (tieneItem) {
            log.info("Guardando imagen para ítem id {}", dto.getItemId());
            Item item = itemRepository.findById(dto.getItemId())
                    .orElseThrow(() -> new RuntimeException("Ítem no existe en la BD"));
            img.setItem(item);
            img.setJuego(null);
        } else {
            log.info("Guardando imagen para juego id {}", dto.getJuegoId());
            Juego juego = juegoRepository.findById(dto.getJuegoId())
                    .orElseThrow(() -> new RuntimeException("Juego no existe en la BD"));
            img.setJuego(juego);
            img.setItem(null);
        }

        return imagenRepository.save(img);
    }
}
