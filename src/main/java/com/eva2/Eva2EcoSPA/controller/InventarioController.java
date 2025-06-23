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
import org.springframework.web.bind.annotation.RestController;

import com.eva2.Eva2EcoSPA.model.Inventario;
import com.eva2.Eva2EcoSPA.service.InventarioService;

@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Inventario>> listarInventario() {
        return ResponseEntity.ok(inventarioService.obtenerInventarios());
    }

    @PostMapping
    public ResponseEntity<Inventario> crearInventario(@RequestBody Inventario inventario) {
        return ResponseEntity.status(201).body(inventarioService.guardar(inventario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerInventario(@PathVariable int id) {
        Inventario inventario = inventarioService.buscarPorId(id);
        return inventario != null ? ResponseEntity.ok(inventario) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizarInventario(
            @PathVariable int id,
            @RequestBody Inventario inventarioActualizado) {
        Inventario inventario = inventarioService.actualizar(id, inventarioActualizado);
        return inventario != null ? ResponseEntity.ok(inventario) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(@PathVariable int id) {
        if (inventarioService.buscarPorId(id) != null) {
            inventarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
