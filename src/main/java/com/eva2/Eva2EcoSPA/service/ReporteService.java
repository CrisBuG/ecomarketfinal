package com.eva2.Eva2EcoSPA.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eva2.Eva2EcoSPA.Repository.ReporteRepository;
import com.eva2.Eva2EcoSPA.model.Reporte;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReporteService {
    @Autowired
    private ReporteRepository reporteRepository;

    public List<Reporte> obtenerReportes() {
        return reporteRepository.findAll();
    }

    public Reporte guardar(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public Reporte buscarPorId(int id) {
        return reporteRepository.findById(id).get();
    }

    public void eliminar(int id) {
        reporteRepository.deleteById(id);
    }

    public Reporte actualizar(int id, Reporte reporteActualizado) {
        return reporteRepository.save(reporteActualizado);

    }
}