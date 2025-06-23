package com.eva2.Eva2EcoSPA.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eva2.Eva2EcoSPA.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // Buscar por nombre del producto
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    // Buscar por categoría
    List<Producto> findByCategoria(String categoria);

    // Buscar productos con stock bajo
    List<Producto> findByStockLessThan(int cantidadMinima);
}
