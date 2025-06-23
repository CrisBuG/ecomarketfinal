package com.eva2.Eva2EcoSPA.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eva2.Eva2EcoSPA.Repository.InventarioRepository;
import com.eva2.Eva2EcoSPA.model.Inventario;

@Service
public class InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    public Inventario buscarPorId(int id) {
        return inventarioRepository.findById(id).orElse(null);
    }

    public List<Inventario> obtenerInventarios() {
        return inventarioRepository.findAll();
    }

    public Inventario guardar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public Inventario actualizar(int id, Inventario inventarioActualizado) {
        return inventarioRepository.findById(id)
                .map(i -> {
                    i.setIdProducto(inventarioActualizado.getIdProducto());
                    i.setCantidadDisponible(inventarioActualizado.getCantidadDisponible());
                    i.setCantidadMinima(inventarioActualizado.getCantidadMinima());
                    i.setUbicacionAlmacen(inventarioActualizado.getUbicacionAlmacen());
                    i.setAlertaBajoStock(inventarioActualizado.isAlertaBajoStock());
                    return inventarioRepository.save(i);
                }).orElse(null);
    }

    public void eliminar(int id) {
        inventarioRepository.deleteById(id);
    }
    

    


}
