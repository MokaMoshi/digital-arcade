package com.digital_arcade_spring.digital_arcade.service;

import com.digital_arcade_spring.digital_arcade.DTO.MetodoPagoDTO;
import com.digital_arcade_spring.digital_arcade.model.MetodoPago;
import com.digital_arcade_spring.digital_arcade.repository.MetodoPagoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Transactional
    public MetodoPago crearMetodoPago(MetodoPagoDTO dto) {
        log.info("Habilitando método de pago para la comunidad: {}", dto.getNombre());
        MetodoPago metodo = new MetodoPago();
        metodo.setNombre(dto.getNombre());
        metodo.setActivo(true);
        return metodoPagoRepository.save(metodo);
    }
}
