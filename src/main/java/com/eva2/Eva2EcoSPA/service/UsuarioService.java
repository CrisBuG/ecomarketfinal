package com.eva2.Eva2EcoSPA.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eva2.Eva2EcoSPA.Repository.UsuarioRepository;
import com.eva2.Eva2EcoSPA.model.Usuario;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(int id) {
        return usuarioRepository.findById(id).get();
    }

    public void eliminar(int id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario actualizar(int id, Usuario usuarioActualizado) {
        return usuarioRepository.save(usuarioActualizado);

    }

}