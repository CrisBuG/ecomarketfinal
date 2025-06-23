package com.eva2.Eva2EcoSPA.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eva2.Eva2EcoSPA.model.Producto;
import com.eva2.Eva2EcoSPA.service.ProductoService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
   
    @Autowired
    private ProductoService productoService;

    // LISTAR TODOS LOS PRODUCTOS
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoService.obtenerProductos());
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> listarPorCategoria(@PathVariable String categoria) {

        return ResponseEntity.ok(productoService.buscarPorCategoria(categoria));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(productoService.buscarPorNombre(nombre));
    }

    // CREAR NUEVO PRODUCTO
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        return ResponseEntity.status(201).body(productoService.guardar(producto));
    }

    // OBTENER UN PRODUCTO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable int id) {
        Producto producto = productoService.buscarPorId(id);
        return (producto != null)
                ? ResponseEntity.ok(producto)
                : ResponseEntity.notFound().build();
    }

    // ACTUALIZAR UN PRODUCTO
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable int id,
            @RequestBody Producto productoActualizado) {
        Producto producto = productoService.actualizar(id, productoActualizado);
        return (producto != null)
                ? ResponseEntity.ok(producto)
                : ResponseEntity.notFound().build();
    }

    // ELIMINAR UN PRODUCTO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable int id) {
        if (productoService.buscarPorId(id) != null) {
            productoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
