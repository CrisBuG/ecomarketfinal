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

import com.eva2.Eva2EcoSPA.model.Reporte;
import com.eva2.Eva2EcoSPA.service.ReporteService;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<List<Reporte>> listarReportes() {
        return ResponseEntity.ok(reporteService.obtenerReportes());
    }

    @PostMapping
    public ResponseEntity<Reporte> crearReporte(@RequestBody Reporte reporte) {
        return ResponseEntity.status(201).body(reporteService.guardar(reporte));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> obtenerReporte(@PathVariable int id) {
        Reporte reporte = reporteService.buscarPorId(id);
        return reporte != null ? ResponseEntity.ok(reporte) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reporte> actualizarReporte(
            @PathVariable int id,
            @RequestBody Reporte reporteActualizado) {
        Reporte reporte = reporteService.actualizar(id, reporteActualizado);
        return reporte != null ? ResponseEntity.ok(reporte) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable int id) {
        if (reporteService.buscarPorId(id) != null) {
            reporteService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
