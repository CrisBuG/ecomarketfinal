package com.eva2.Eva2EcoSPA.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eva2.Eva2EcoSPA.Repository.ProductoRepository;
import com.eva2.Eva2EcoSPA.model.Producto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto buscarPorId(int id) {
        return productoRepository.findById(id).get();
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }
    
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminar(int id) {
        productoRepository.deleteById(id);
    }

    public Producto actualizar(int id, Producto productoActualizado) {
        return productoRepository.save(productoActualizado);
    }
}