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

import com.eva2.Eva2EcoSPA.model.Pedido;
import com.eva2.Eva2EcoSPA.service.PedidoService;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.obtenerPedidos());
    }

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.status(201).body(pedidoService.guardar(pedido));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedido(@PathVariable int id) {
        Pedido pedido = pedidoService.buscarPorId(id);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(
            @PathVariable int id,
            @RequestBody Pedido pedidoActualizado) {
        Pedido pedido = pedidoService.actualizar(id, pedidoActualizado);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable int id) {
        if (pedidoService.buscarPorId(id) != null) {
            pedidoService.elimarPorId(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
