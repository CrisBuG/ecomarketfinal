package com.eva2.Eva2EcoSPA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eva2.Eva2EcoSPA.model.Envio;

public interface EnvioRepository extends JpaRepository<Envio, Integer> {

    // Buscar envíos por estado
    List<Envio> findByEstado(String estado);

    // Buscar envíos por ID de pedido - CORREGIDO
    List<Envio> findByPedidoId(int pedidoId);

    // Buscar envíos por transportista
    List<Envio> findByTransportista(String transportista);
}