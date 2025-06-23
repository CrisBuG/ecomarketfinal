package com.eva2.Eva2EcoSPA.controller;

import com.eva2.Eva2EcoSPA.model.Producto;
import com.eva2.Eva2EcoSPA.service.ProductoService;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testListarProductos() throws Exception {
        Producto producto1 = new Producto();
        producto1.setId(1);
        producto1.setNombre("Masaje Relajante");
        producto1.setDescripcion("Masaje de relajación completa");
        producto1.setPrecio(50000.0);
        producto1.setStock(10);
        producto1.setCategoria("Masajes");

        Producto producto2 = new Producto();
        producto2.setId(2);
        producto2.setNombre("Facial Hidratante");
        producto2.setDescripcion("Tratamiento facial hidratante");
        producto2.setPrecio(35000.0);
        producto2.setStock(5);
        producto2.setCategoria("Faciales");

        List<Producto> productos = Arrays.asList(producto1, producto2);

        when(productoService.obtenerProductos()).thenReturn(productos);

        mockMvc.perform(get("/api/v1/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].nombre").value("Masaje Relajante"))
                .andExpect(jsonPath("$[1].nombre").value("Facial Hidratante"));
    }

    @Test
    public void testCrearProducto() throws Exception {
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre("Masaje Deportivo");
        nuevoProducto.setDescripcion("Masaje para deportistas");
        nuevoProducto.setPrecio(60000.0);
        nuevoProducto.setStock(8);
        nuevoProducto.setCategoria("Masajes");

        Producto productoGuardado = new Producto();
        productoGuardado.setId(3);
        productoGuardado.setNombre("Masaje Deportivo");
        productoGuardado.setDescripcion("Masaje para deportistas");
        productoGuardado.setPrecio(60000.0);
        productoGuardado.setStock(8);
        productoGuardado.setCategoria("Masajes");

        when(productoService.guardar(any(Producto.class))).thenReturn(productoGuardado);

        mockMvc.perform(post("/api/v1/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoProducto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.nombre").value("Masaje Deportivo"));
    }

    @Test
    public void testObtenerProductoPorId() throws Exception {
        Producto producto = new Producto();
        producto.setId(1);
        producto.setNombre("Masaje Relajante");
        producto.setDescripcion("Masaje de relajación completa");
        producto.setPrecio(50000.0);
        producto.setStock(10);
        producto.setCategoria("Masajes");

        when(productoService.buscarPorId(1)).thenReturn(producto);

        mockMvc.perform(get("/api/v1/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Masaje Relajante"));
    }

    @Test
    public void testObtenerProductoPorIdNoEncontrado() throws Exception {
        when(productoService.buscarPorId(999)).thenReturn(null);

        mockMvc.perform(get("/api/v1/productos/999"))
                .andExpect(status().isNotFound());
    }
}