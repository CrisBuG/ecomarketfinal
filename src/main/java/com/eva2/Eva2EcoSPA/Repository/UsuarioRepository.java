package com.eva2.Eva2EcoSPA.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eva2.Eva2EcoSPA.model.Usuario;
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Buscar usuario por email
    Optional<Usuario> findByEmail(String email);

    // Buscar usuarios por rol
    List<Usuario> findByRol(String rol);
     
}
