package com.eva2.Eva2EcoSPA.controller;

import com.eva2.Eva2EcoSPA.model.Usuario;
import com.eva2.Eva2EcoSPA.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testListarUsuarios() throws Exception {
        Usuario usuario1 = new Usuario();
        usuario1.setId(1);
        usuario1.setNombre("Juan Pérez");
        usuario1.setEmail("juan@email.com");
        usuario1.setPassword("password123");
        usuario1.setRun("12345678");
        usuario1.setRol("CLIENTE");
        
        Usuario usuario2 = new Usuario();
        usuario2.setId(2);
        usuario2.setNombre("María González");
        usuario2.setEmail("maria@email.com");
        usuario2.setPassword("password456");
        usuario2.setRun("87654321");
        usuario2.setRol("ADMIN");
        
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);
        
        when(usuarioService.obtenerUsuarios()).thenReturn(usuarios);

        mockMvc.perform(get("/api/v1/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].nombre").value("Juan Pérez"))
                .andExpect(jsonPath("$[1].nombre").value("María González"));
    }

    @Test
    public void testCrearUsuario() throws Exception {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("Carlos López");
        nuevoUsuario.setEmail("carlos@email.com");
        nuevoUsuario.setPassword("password789");
        nuevoUsuario.setRun("11223344");
        nuevoUsuario.setRol("CLIENTE");
        
        Usuario usuarioGuardado = new Usuario();
        usuarioGuardado.setId(3);
        usuarioGuardado.setNombre("Carlos López");
        usuarioGuardado.setEmail("carlos@email.com");
        usuarioGuardado.setPassword("password789");
        usuarioGuardado.setRun("11223344");
        usuarioGuardado.setRol("CLIENTE");
        
        when(usuarioService.guardar(any(Usuario.class))).thenReturn(usuarioGuardado);

        mockMvc.perform(post("/api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoUsuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.nombre").value("Carlos López"));
    }

    @Test
    public void testObtenerUsuarioPorId() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Ana Martínez");
        usuario.setEmail("ana@email.com");
        usuario.setPassword("password123");
        usuario.setRun("55667788");
        usuario.setRol("GERENTE_TIENDA");
        
        when(usuarioService.buscarPorId(1)).thenReturn(usuario);

        mockMvc.perform(get("/api/v1/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ana Martínez"))
                .andExpect(jsonPath("$.rol").value("GERENTE_TIENDA"));
    }
}