package com.eva2.Eva2EcoSPA.controller;

import com.eva2.Eva2EcoSPA.model.Pedido;
import com.eva2.Eva2EcoSPA.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testListarPedidos() throws Exception {
        Pedido pedido1 = new Pedido();
        pedido1.setId(1);
        pedido1.setCantidad(5);
        pedido1.setFechaPedido(new Date());
        pedido1.setEstado("PENDIENTE");
        pedido1.setTotal(250.0);
        
        Pedido pedido2 = new Pedido();
        pedido2.setId(2);
        pedido2.setCantidad(3);
        pedido2.setFechaPedido(new Date());
        pedido2.setEstado("COMPLETADO");
        pedido2.setTotal(150.0);
        
        List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);
        
        when(pedidoService.obtenerPedidos()).thenReturn(pedidos);

        mockMvc.perform(get("/api/v1/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].estado").value("PENDIENTE"))
                .andExpect(jsonPath("$[1].estado").value("COMPLETADO"));
    }

    @Test
    public void testCrearPedido() throws Exception {
        Pedido nuevoPedido = new Pedido();
        nuevoPedido.setCantidad(2);
        nuevoPedido.setFechaPedido(new Date());
        nuevoPedido.setEstado("NUEVO");
        nuevoPedido.setTotal(100.0);
        
        Pedido pedidoGuardado = new Pedido();
        pedidoGuardado.setId(3);
        pedidoGuardado.setCantidad(2);
        pedidoGuardado.setFechaPedido(new Date());
        pedidoGuardado.setEstado("NUEVO");
        pedidoGuardado.setTotal(100.0);
        
        when(pedidoService.guardar(any(Pedido.class))).thenReturn(pedidoGuardado);

        mockMvc.perform(post("/api/v1/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoPedido)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.estado").value("NUEVO"));
    }

    @Test
    public void testObtenerPedidoPorId() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setCantidad(4);
        pedido.setFechaPedido(new Date());
        pedido.setEstado("EN_PROCESO");
        pedido.setTotal(200.0);
        
        when(pedidoService.buscarPorId(1)).thenReturn(pedido);

        mockMvc.perform(get("/api/v1/pedidos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("EN_PROCESO"))
                .andExpect(jsonPath("$.total").value(200.0));
    }
}