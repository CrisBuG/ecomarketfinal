package com.eva2.Eva2EcoSPA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eva2.Eva2EcoSPA.model.Lista;

public interface ListaRepository extends JpaRepository<Lista, Integer> {
     
     List<Lista> findByTipo(String tipo);

     
     List<Lista> findByUsuarioId(int usuarioId);
}