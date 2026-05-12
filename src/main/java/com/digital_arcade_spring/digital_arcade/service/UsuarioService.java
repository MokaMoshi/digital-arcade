package com.digital_arcade_spring.digital_arcade.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digital_arcade_spring.digital_arcade.DTO.UsuarioRegistroDTO;
import com.digital_arcade_spring.digital_arcade.model.Rol;
import com.digital_arcade_spring.digital_arcade.model.Usuario;
import com.digital_arcade_spring.digital_arcade.repository.RolRepository;
import com.digital_arcade_spring.digital_arcade.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Transactional
    public Usuario registrarUsuario(UsuarioRegistroDTO dto) {
        log.info("Registrando usuario: {}", dto.getUsername());

        if (usuarioRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Rol rol = rolRepository.findById(dto.getRolId())
                .orElseThrow(() -> new RuntimeException("No existe un rol con id " + dto.getRolId()));

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setEmail(dto.getEmail());
        usuario.setEstado(dto.getEstado());
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }
}
