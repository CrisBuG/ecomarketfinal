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

import com.eva2.Eva2EcoSPA.model.Envio;
import com.eva2.Eva2EcoSPA.service.EnvioService;

@RestController
@RequestMapping("/api/v1/envios")
public class EnvioController {
    @Autowired
    private EnvioService envioService;

    @GetMapping
    public ResponseEntity<List<Envio>> listarEnvios() {
        return ResponseEntity.ok(envioService.listarEnvios());
    }

    @PostMapping
    public ResponseEntity<Envio> crearEnvio(@RequestBody Envio envio) {
        return ResponseEntity.status(201).body(envioService.guardarEnvio(envio));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> obtenerEnvio(@PathVariable int id) {
        Envio envio = envioService.buscarPorId(id);
        return envio != null ? ResponseEntity.ok(envio) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizarEnvio(
            @PathVariable int id,
            @RequestBody Envio envioActualizado) {
        Envio envio = envioService.updateEnvio(envioActualizado); // Sin parametro id porque ya viene en el objeto
        return envio != null ? ResponseEntity.ok(envio) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEnvio(@PathVariable int id) {
        if (envioService.buscarPorId(id) != null) {
            envioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
