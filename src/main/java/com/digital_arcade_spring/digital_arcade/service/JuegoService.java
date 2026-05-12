package com.digital_arcade_spring.digital_arcade.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digital_arcade_spring.digital_arcade.DTO.JuegoDTO;
import com.digital_arcade_spring.digital_arcade.model.Juego;
import com.digital_arcade_spring.digital_arcade.repository.JuegoRepository;

@Service
public class JuegoService {

    private static final Logger log = LoggerFactory.getLogger(JuegoService.class);

    @Autowired
    private JuegoRepository juegoRepository;

    @Transactional
    public Juego crearJuego(JuegoDTO dto) {
        log.info("Registrando juego: {}", dto.getTitulo());
        Juego juego = new Juego();
        juego.setTitulo(dto.getTitulo());
        juego.setDescripcion(dto.getDescripcion());
        juego.setFechaLanzamiento(dto.getFechaLanzamiento());
        juego.setDesarrollador(dto.getDesarrollador());
        return juegoRepository.save(juego);
    }
}
