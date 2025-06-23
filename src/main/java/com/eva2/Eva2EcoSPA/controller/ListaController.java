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

import com.eva2.Eva2EcoSPA.model.Lista;
import com.eva2.Eva2EcoSPA.service.ListaService;

@RestController
@RequestMapping("/api/v1/listas")
public class ListaController {

    @Autowired
    private ListaService listaService;

    @GetMapping
    public ResponseEntity<List<Lista>> listarListas() {
        return ResponseEntity.ok(listaService.obtenerListas());
    }

    @PostMapping
    public ResponseEntity<Lista> crearLista(@RequestBody Lista lista) {
        return ResponseEntity.status(201).body(listaService.guardarListas(lista));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lista> obtenerLista(@PathVariable int id) {
        Lista lista = listaService.buscarPorId(id);
        return lista != null ? ResponseEntity.ok(lista) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lista> actualizarLista(
            @PathVariable int id,
            @RequestBody Lista listaActualizada) {
        Lista lista = listaService.actualizarListas(id, listaActualizada);
        return lista != null ? ResponseEntity.ok(lista) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLista(@PathVariable int id) {
        if (listaService.buscarPorId(id) != null) {
            listaService.borrarPorId(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
