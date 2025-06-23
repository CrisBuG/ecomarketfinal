package com.eva2.Eva2EcoSPA.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eva2.Eva2EcoSPA.Repository.ListaRepository;
import com.eva2.Eva2EcoSPA.model.Lista;

import jakarta.transaction.Transactional;

// Esta clase hace referencia a un inventario

@Service
@Transactional
public class ListaService {
    @Autowired
    private ListaRepository listaRepository;
    
    public List<Lista> obtenerListas() {
        return listaRepository.findAll();
    }

    public Lista buscarPorId(int id) {
        return listaRepository.findById(id).get();
    }

    public Lista guardarListas(Lista lista) {
        return listaRepository.save(lista);
    }

    public Lista actualizarListas(int id, Lista listaActualizada) {
        listaActualizada.setId(id);
        return listaRepository.save(listaActualizada);
    }

    public void borrarPorId(int id) {
        listaRepository.deleteById(id);
    }

}
