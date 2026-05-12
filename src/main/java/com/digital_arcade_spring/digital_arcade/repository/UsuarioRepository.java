package com.digital_arcade_spring.digital_arcade.repository;

import com.digital_arcade_spring.digital_arcade.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
