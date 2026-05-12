package com.digital_arcade_spring.digital_arcade.service;

import com.digital_arcade_spring.digital_arcade.DTO.ImagenDTO;
import com.digital_arcade_spring.digital_arcade.model.Imagen;
import com.digital_arcade_spring.digital_arcade.model.Item;
import com.digital_arcade_spring.digital_arcade.repository.ImagenRepository;
import com.digital_arcade_spring.digital_arcade.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@slf4j
public class ImagenService {

    private static final Logger log = LoggerFactory.getLogger(ImagenService.class);

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public Imagen agregarImagen(ImagenDTO dto) {
        log.info("Agregando recurso visual para ítem ID: {}", dto.getItemId());
        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new RuntimeException("Ítem principal no existe en la BD"));

        Imagen img = new Imagen();
        img.setUrl(dto.getUrl());
        img.setAltText(dto.getAltText());
        img.setItem(item);
        return imagenRepository.save(img);
    }
}
