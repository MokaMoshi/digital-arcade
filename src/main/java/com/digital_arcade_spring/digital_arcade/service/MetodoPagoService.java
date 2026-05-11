package com.digital_arcade_spring.digital_arcade.service;

import com.digital_arcade_spring.digital_arcade.dto.MetodoPagoDTO;
import com.digital_arcade_spring.digital_arcade.model.MetodoPago;
import com.digital_arcade_spring.digital_arcade.repository.MetodoPagoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MetodoPagoService {

    private static final Logger log = LoggerFactory.getLogger(MetodoPagoService.class);

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Transactional
    public MetodoPago registrarMetodo(MetodoPagoDTO dto) {
        log.info("Habilitando método de pago para la comunidad: {}", dto.getNombre());
        MetodoPago metodo = new MetodoPago();
        metodo.setNombre(dto.getNombre());
        metodo.setActivo(true);
        return metodoPagoRepository.save(metodo);
    }
}
