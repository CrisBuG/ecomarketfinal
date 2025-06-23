package com.eva2.Eva2EcoSPA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eva2.Eva2EcoSPA.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    // Buscar pedidos por cliente - CORREGIDO
    List<Pedido> findByUsuarioId(int usuarioId);

    // Buscar pedidos por estado
    List<Pedido> findByEstado(String estado);

    // Buscar pedidos por producto - CORREGIDO
    List<Pedido> findByProductoId(int productoId);
}