package com.eva2.Eva2EcoSPA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eva2.Eva2EcoSPA.model.Reporte;
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {

    // Buscar reportes por tipo
    List<Reporte> findByTipo(String tipo);

    // Buscar reportes por formato
    List<Reporte> findByFormato(String formato);
}
