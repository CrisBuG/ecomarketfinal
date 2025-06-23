package com.eva2.Eva2EcoSPA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eva2.Eva2EcoSPA.model.Inventario;
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

    // Buscar inventario por ID de producto
    Inventario findByIdProducto(int idProducto);

    // Buscar inventarios con alerta de bajo stock
    List<Inventario> findByAlertaBajoStock(boolean alerta);

    // Buscar inventarios por ubicación
    List<Inventario> findByUbicacionAlmacen(String ubicacion);

}