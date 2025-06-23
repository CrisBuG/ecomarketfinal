package com.eva2.Eva2EcoSPA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eva2.Eva2EcoSPA.Repository.PedidoRepository;
import com.eva2.Eva2EcoSPA.model.Pedido;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    
    public List<Pedido> obtenerPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(int id) {
        return pedidoRepository.findById(id).get();
    }

    public Pedido guardar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void elimarPorId(int id) {
        pedidoRepository.deleteById(id);
    }

    public Pedido actualizar(int id, Pedido pedidoActualizado) {
        return pedidoRepository.save(pedidoActualizado);
    }  
}