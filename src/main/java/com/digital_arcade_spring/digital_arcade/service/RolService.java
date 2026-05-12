package com.digital_arcade_spring.digital_arcade.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digital_arcade_spring.digital_arcade.DTO.RolDTO;
import com.digital_arcade_spring.digital_arcade.model.Rol;
import com.digital_arcade_spring.digital_arcade.repository.RolRepository;

@Service
public class RolService {

    private static final Logger log = LoggerFactory.getLogger(RolService.class);

    @Autowired
    private RolRepository rolRepository;

    @Transactional
    public Rol crearRol(RolDTO dto) {
        log.info("Registrando nuevo rol: {}", dto.getNombre());
        Rol rol = new Rol();
        rol.setNombre(dto.getNombre());
        rol.setDescripcion(dto.getDescripcion());
        return rolRepository.save(rol);
    }
}
